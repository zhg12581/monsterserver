package com.example.common;

import lombok.Getter;

/**
 * 管理员账号状态枚举类
 * 0：正常
 * 1：冻结
 * @author zhu
 * @version 1.0
 * @date 2019/12/15 下午8:47
 */
@Getter
public enum AdminStatusCodeEnum {
    /** 正常 */
    NORMAL(0,"NORMAL","正常"),

    /** 冻结 */
    LOCKING(1,"LOCKING","冻结");

    /** 状态码 */
    private final Integer code;

    /** 字段名称 */
    private final String name;

    /** 状态描述 */
    private final String desc;

    /** 该枚举类中最大索引值 */
    public static final Integer MAX_INDEX = 1;

    /** 该枚举类中最小索引值 */
    public static final Integer MIN_INDEX = 0;

    AdminStatusCodeEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
}
