package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 管理员实体类
 * @author zhu
 * @version 1.0
 * @date 2019/12/14 下午9:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdminDO {
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
}
