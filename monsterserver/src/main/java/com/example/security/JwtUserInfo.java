package com.example.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/22 下午10:44
 */
@Data
public class JwtUserInfo implements UserDetails{
    /**管理员ID*/
    private Integer id;

    /**管理员账号*/
    private String userName;

    /**管理员密码*/
    private String password;

    /**管理员名称*/
    private String name;

    /** 账号状态 */
    private Integer status;

    /** 管理员最后登录记录IP地址 */
    private String loginIpAddr;

    /** 最后登录的时间 */
    private Date loginTime;

    /** 管理员信息创建时间戳 */
    private Date createTime;

    /** 管理员信息更新时间 */
    private Date updateTime;

    /**
     * 权限信息
     */
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserInfo(Integer id, String userName, String password, String name, Integer status, String loginIpAddr, Date loginTime, Date createTime, Date updateTime, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.status = status;
        this.loginIpAddr = loginIpAddr;
        this.loginTime = loginTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
