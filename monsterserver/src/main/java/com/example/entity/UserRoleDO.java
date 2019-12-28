package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/23 上午11:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRoleDO {
    private Integer id;

    //用户id
    private Integer uid;

    //角色id
    private Integer rid;
}
