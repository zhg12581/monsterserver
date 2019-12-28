package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目页面配置器
 * @author zhu
 * @version 1.0
 * @date 2019/12/19 下午10:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 允许cors跨域请求
     *
     * @param registry 跨域注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowedOrigins("*").allowedMethods("*");
    }
}
