package com.example.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 自定义JSON响应结构
 * 前端请求的数据将以JSON格式转换并响应
 * 状态码（status）
 * 310：表示Server端理解了请求，成功处理并返回
 * 620：表示请求失败
 * 623：表示请求参数错误
 * 632：表示该方法不支持或已经废弃
 * 644：表示当前请求需登录后操作
 * 675：表示频繁请求
 * 764：表示服务器内部异常
 * @author zhu
 * @version 1.0
 * @date 2019/12/15 下午8:35
 */
@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult {
    /** Jackson对象 */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** 响应业务状态码 */
    private Integer status;

    /** 响应信息 */
    private String statusInfo;

    /** 响应中的数据 */
    private Object data;

    /**
     * 判断当前响应结果是否成功
     *
     * @return true or false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == StatusCodeEnum.SUCCESS.getCode();
    }

    /**
     * 快速构建自定义响应结构体
     * @param status 状态码
     * @param statusInfo 状态信息
     * @param data 数据
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult build(Integer status, String statusInfo, Object data){
        return new ResponseResult(status,statusInfo,data);
    }

    /**
     * 根据对象快速构建成功状态响应结构体
     * @param data 数据对象
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult success(Object data){
        return new ResponseResult(data);
    }

    /**
     * 根据所需返回消息快速构建成功状态响应结构体
     * @param message 所需返回的消息
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult success(String message){
        return new ResponseResult(StatusCodeEnum.SUCCESS.getCode(),message,null);
    }

    /**
     * 快速构建无数据成功状态响应结构体
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult success(){
        return new ResponseResult(null);
    }

    /**
     * 根据所要提示的消息构建失败状态响应结构体
     * @param msg 所要返回的错误信息
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult error(String msg){
        return new ResponseResult(StatusCodeEnum.ERROR.getCode(),msg,null);
    }

    /**
     * 快速构建无数据失败状态响应结构体
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult error(){return new ResponseResult(StatusCodeEnum.ERROR.getCode(),StatusCodeEnum.ERROR.getDesc(),null);}

    /**
     * 构建请求方法错误状态响应结构体
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult errorMethod(){
        return new ResponseResult(StatusCodeEnum.NOT_SUPPORTED.getCode(), StatusCodeEnum.NOT_SUPPORTED.getDesc(),null);
    }

    /**
     * 根据所传递的message构建错误参数响应结构体
     * @param msg 所需记录的状态信息
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult errorParam(String msg){
        return new ResponseResult(StatusCodeEnum.PARAMS_ERROR.getCode(),msg,null);
    }

    /**
     * 构建错误参数响应结构体
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult errorParam(){
        return new ResponseResult(StatusCodeEnum.PARAMS_ERROR.getCode(),StatusCodeEnum.PARAMS_ERROR.getDesc(),null);
    }

    /**
     * 根据所传递的message构建异常状态响应结构体
     * @param msg 所需记录返回的异常信息
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult errorException(String msg){
        return new ResponseResult(StatusCodeEnum.EXCEPTION.getCode(),msg,null);
    }

    /**
     * 提供静态方法以将JSON结果集转化为响应结构体
     * @param jsonData 所需转换的JSON结果
     * @param clazz 所需转换JSON结果集中的data是一个类
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult formatToPojo(String jsonData, Class<?> clazz){
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResponseResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = MAPPER.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = MAPPER.readValue(data.asText(), clazz);
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("statusInfo").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 提供静态方法以将JSON数据转化为响应体，所需转换的对象是一个list
     * @param jsonData JSON数据
     * @param clazz 转换的对象
     * @return org.gzmtu.monster.common.ResponseResult
     */
    public static ResponseResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("statusInfo").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseResult() {
    }

    private ResponseResult(Integer status, String statusInfo, Object data) {
        this.status = status;
        this.statusInfo = statusInfo;
        this.data = data;
    }

    private ResponseResult(Object data) {
        this.status = StatusCodeEnum.SUCCESS.getCode();
        this.statusInfo = StatusCodeEnum.SUCCESS.getDesc();
        this.data = data;
    }


}
