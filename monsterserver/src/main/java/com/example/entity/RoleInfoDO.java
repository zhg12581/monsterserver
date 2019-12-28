package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/22 下午10:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleInfoDO {

    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色功能说明
     */
    private String roleDescription;

    private Date createTime;

    private Date updateTime;
}
