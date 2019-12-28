package com.example.security;

import com.example.common.ResponseResult;
import com.example.common.StatusCodeEnum;
import com.example.util.HttpUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhu 自定义访问拒绝处理器(登录状态下无权限)
 * @version 1.0
 * @date 2019/12/23 上午11:59
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult responseResult = ResponseResult.build(StatusCodeEnum.ERROR.getCode(), "无权限,拒绝访问", null);
        HttpUtils.sendJson(httpServletResponse, responseResult);
    }
}
