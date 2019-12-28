package com.example.security;

import com.example.util.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/23 下午12:01
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        JwtUserInfo jwtUserInfo = (JwtUserInfo) authentication.getPrincipal();
        String token = jwtTokenUtils.generateToken(jwtUserInfo);

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        Map<String, String> map = new HashMap<>();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        map.put("status", "200");
        map.put("msg", "登录成功");
        map.put("detail",userDetails.toString());
        map.put("token",token);
        httpServletResponse.getWriter().append(objectMapper.writeValueAsString(map));
    }
}
