package com.example.common;

import lombok.Getter;

/**
 * 输出JSON字段status的值由该枚举类决定
 * @author zhu
 * @version 1.0
 * @date 2019/12/15 下午8:38
 */
@Getter
public enum StatusCodeEnum {
    /** 成功处理 */
    SUCCESS(310,"SUCCESS","请求发送成功"),

    /** 失败处理 */
    ERROR(620,"ERROR","请求发生错误"),

    /** 参数错误 */
    PARAMS_ERROR(623,"PARAMS_ERROR","请求参数错误"),

    /** 不支持或已经废弃 */
    NOT_SUPPORTED(632,"NOT_SUPPORTED","方法不支持或已经废弃"),

    /** 未登录 */
    NOT_LOGIN(644, "NOT_LOGIN","当前未登录"),

    /** 频繁调用 */
    TOO_FREQUENT(675,"TOO_FREQUENT","频繁调用"),

    /** 发生异常 */
    EXCEPTION(764,"EXCEPTION","服务器发生异常"),

    /** 闯关结束 */
    ADVENTURE_END(777, "ADVENTURE_END", "闯关结束");

    /** 状态码 */
    private final int code;

    /** 字段名称 */
    private final String name;

    /** 状态信息 */
    private final String desc;

    StatusCodeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
}
