package com.example.security;

import com.example.common.ResponseResult;
import com.example.common.StatusCodeEnum;
import com.example.util.HttpUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhu 自定义拒绝访问处理器(用户未登录）
 * @version 1.0
 * @date 2019/12/23 上午11:59
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult responseResult = ResponseResult.build(StatusCodeEnum.NOT_LOGIN.getCode(), "拒绝访问,当前未登录", null);
        HttpUtils.sendJson(httpServletResponse, responseResult);
    }
}
