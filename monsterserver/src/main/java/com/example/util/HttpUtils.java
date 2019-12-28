package com.example.util;

import com.example.common.Constants;
import com.example.common.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Http工具类
 * @author zhu
 * @version 1.0
 * @date 2019/12/15 下午9:02
 */
public class HttpUtils {
    private static final String IP_SPLIT_CHAR = ",";

    private static final String LOCAL_IPADDRESS = "0:0:0:0:0:0:0:1";

    /**
     * 响应Json给前端
     *
     * @param httpServletResponse 响应对象
     * @param responseResult      响应结果对象
     * @throws IOException io异常
     */
    public static void sendJson(HttpServletResponse httpServletResponse, ResponseResult responseResult) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JsonUtils.objectToJson(responseResult));
    }

    /**
     * 获取请求的IP地址并返回（反向代理工具也可获取真实IP地址）
     * @param request 请求对象，所需要的IP地址从请求对象中获取
     * @return java.lang.String
     */
    public static String getIpAddress(HttpServletRequest request){
        String xIp = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(xFor) && !Constants.UNKNOWN.equalsIgnoreCase(xFor)){
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(IP_SPLIT_CHAR);
            if(index != -1){
                return xFor.substring(0,index);
            }else{
                return xFor;
            }
        }
        xFor = xIp;
        if(StringUtils.isNotEmpty(xFor) && !Constants.UNKNOWN.equalsIgnoreCase(xFor)){
            return xFor;
        }
        if (StringUtils.isBlank(xFor) || Constants.UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xFor) || Constants.UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xFor) || Constants.UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(xFor) || Constants.UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(xFor) || Constants.UNKNOWN.equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }
        if(xFor.equalsIgnoreCase(LOCAL_IPADDRESS)){
            xFor = "127.0.0.1";
        }
        return xFor;
    }
}
