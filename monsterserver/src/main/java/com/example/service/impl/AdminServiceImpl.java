package com.example.service.impl;

import com.example.dto.backend.question.AdminDTO;
import com.example.entity.AdminDO;
import com.example.mapper.AdminMapper;
import com.example.service.AdminService;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/14 下午10:21
 */
@Service
public class AdminServiceImpl implements AdminService{
    /**
     * 管理员信息数据库操作接口
     */
    @Resource
    private AdminMapper adminMapper;

    /**
     * 密码加密工具
     */
    private PasswordEncoder passwordEncoder;

    /**
     * 根据传入的管理员对象下对管理员信息进行验证，并返回验证结果
     *
     * @param adminDO 所需验证的对象
     * @return boolean
     */
    @Override
    public boolean verification(@NonNull AdminDO adminDO) {
        /*AdminDO temp;
        if (null != adminDO.getUserName()) {
            temp = adminMapper.getAdminByUserName(adminDO.getUserName());
            return (null != temp && passwordEncoder.matches(adminDO.getPassword(), temp.getPassword()));
        }
        return false;*/
        AdminDO temp;
        if (null != adminDO.getUserName()) {
            temp = adminMapper.getAdminByUserName(adminDO.getUserName());
            if(null!=temp)
            {
                if(temp.getPassword().equals(adminDO.getPassword())) {
                    return true;
                }
                return false;
            }
            return false;

        }
        return false;
    }

    /**
     * 根据DTO管理员对象转换为DO对象
     *
     * @param adminDTO 所需转换的管理员信息传递对象
     * @return org.gzmtu.monster.dto.backend.AdminDTO
     */
    @Override
    public AdminDO transformDTO(AdminDTO adminDTO) {
        AdminDO adminDO = new AdminDO();
        adminDO.setUserName(adminDTO.getUserName())
                .setPassword(adminDTO.getPassword());
        return adminDO;
    }

    /**
     * 根据管理员账号吗向数据库中查询并生成对象返回
     *
     * @param userName 所需查询的管理员账号名
     * @return org.gzmtu.monster.entity.AdminDO
     */
    @Override
    public AdminDO getAdminByUserName(@NotBlank String userName) {
        return adminMapper.getAdminByUserName(userName);
    }

    /**
     * 插入或更新新管理员记录
     *
     * @param adminDO 所需插入或更新的管理员对象
     * @return boolean
     */
    @Override
    public boolean saveOrUpdate(@NonNull AdminDO adminDO) {
        if (null == adminDO.getId() || null == adminMapper.getAdmin(adminDO.getId())) {
            adminMapper.insert(adminDO);
        } else {
            adminMapper.update(adminDO);
        }
        return true;
    }


}
