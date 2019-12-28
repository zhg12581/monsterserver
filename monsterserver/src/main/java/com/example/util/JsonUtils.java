package com.example.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.List;

/**
 * JSON工具类
 * @author zhu
 * @version 1.0
 * @date 2019/12/15 下午9:02
 */
public class JsonUtils {
    /** Jackson对象 */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串
     * @param data 所需转换的对象
     * @return java.lang.String
     */
    public static String objectToJson(@NonNull Object data) throws JsonProcessingException {
        return MAPPER.writeValueAsString(data);
    }

    /**
     * 将json结果集转化为对象
     * @param jsonData 所需转换的JSON数据
     * @param beanType 所需转换的类
     * @return T 转换对象结果
     */
    public static <T> T jsonToPojo(@NonNull String jsonData,@NonNull Class<T> beanType) throws IOException {
        return MAPPER.readValue(jsonData, beanType);
    }

    /**
     * 将JSON数据转换成pojo对象list
     * @param jsonData 所需转换的JSON数据
     * @param beanType 所需转换类型
     * @return java.util.List<T>
     */
    public static <T> List<T> jsonToList(@NonNull String jsonData,@NonNull Class<T> beanType) throws IOException {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        return MAPPER.readValue(jsonData, javaType);
    }

    /**
     * 根据所传递的字符串和键验证是否属于本系统的ID
     * @param jsonString 所需检测的字符串
     * @param jsonArrayKey json数组提取关键字
     * @param jsonObjectKey json对象提取关键字
     */
    public static String isValidIdsJson(String jsonString, String jsonArrayKey, String jsonObjectKey){
        JSONArray idJsonArray;
        try {
            if (!JSONObject.isValidObject(jsonString)) {
                return "参数格式异常";
            }
            idJsonArray = JSONObject.parseObject(jsonString).getJSONArray(jsonArrayKey);
        } catch (Exception e) {
            return "参数格式异常";
        }
        String idStr;
        for (int index = 0,count = 0; index < idJsonArray.size(); index++,count=0) {
            idStr = ((JSONObject) idJsonArray.get(index)).getString(jsonObjectKey);
            if (null == idStr || !idStr.matches("^[0-9]{1,10}$") || Integer.valueOf(idStr) > Integer.MAX_VALUE) {
                return "检测到ID有误";
            }
            for (Object jsonObject : idJsonArray) {
                if (((JSONObject) jsonObject).get(jsonObjectKey).equals(idStr) && ++count != 1) {
                    return "检测到ID重复";
                }
            }
        }
        return null;
    }
}
