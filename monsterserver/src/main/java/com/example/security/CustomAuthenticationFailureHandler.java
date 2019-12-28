package com.example.security;

import com.example.common.ResponseResult;
import com.example.common.StatusCodeEnum;
import com.example.util.HttpUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/23 下午12:00
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult responseResult = ResponseResult.build(StatusCodeEnum.ERROR.getCode(), e.getMessage(), null);
        HttpUtils.sendJson(httpServletResponse, responseResult);
    }
}
