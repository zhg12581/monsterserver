package com.example.security;

import com.example.common.ResponseResult;
import com.example.common.StatusCodeEnum;
import com.example.util.HttpUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出成功处理器
 * @author zhu
 * @version 1.0
 * @date 2019/12/24 下午4:45
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseResult responseResult = ResponseResult.build(StatusCodeEnum.SUCCESS.getCode(), "退出成功", null);
        HttpUtils.sendJson(httpServletResponse, responseResult);
    }
}
