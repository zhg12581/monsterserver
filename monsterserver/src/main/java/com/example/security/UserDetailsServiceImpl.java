package com.example.security;

import com.example.entity.AdminDO;
import com.example.entity.RoleInfoDO;
import com.example.mapper.AdminMapper;
import com.example.mapper.LoginMapper;
import com.example.security.JwtUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/23 上午10:46
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService{
    @Resource
    private AdminMapper adminMapper;

    @Resource
    private LoginMapper loginMapper;

    @Override
    @ResponseBody
    public UserDetails loadUserByUsername(@RequestBody String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        // 通过用户名从数据库获取用户信息
        AdminDO adminDO=adminMapper.getAdminByUserName(username);
        if (adminDO == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<RoleInfoDO> roleInfo=loginMapper.getUserRolesByUid(adminDO.getId());
        for (RoleInfoDO roleInfoDO : roleInfo) {
            authorityList.add(new SimpleGrantedAuthority(roleInfoDO.getRoleName()));
        }
      //  authorityList.add(new SimpleGrantedAuthority(loginMapper.getUserRolesByUid(adminDO.getId()).getRoleName()));
        return new JwtUserInfo(adminDO.getId(),adminDO.getUserName(),adminDO.getPassword(),adminDO.getName(),adminDO.getStatus(),adminDO.getLoginIpAddr(),adminDO.getLoginTime(),adminDO.getCreateTime(),adminDO.getUpdateTime(),authorityList);
    }
}
