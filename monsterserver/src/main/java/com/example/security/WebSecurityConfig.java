package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhu
 * @version 1.0
 * @date 2019/12/23 下午3:05
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 密码不加密，使用明文方式
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        };
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 自定义页面的路径不用验证
               // .antMatchers("/backend/login").permitAll()
                //.antMatchers("/backend/try").hasRole("admin")
                //除了前面定义的URL模式外，用户访问其他的URL都必须登录后访问
              //  .antMatchers("/backend/LoginIn").permitAll()
                .antMatchers("/backend/see").permitAll()
                .antMatchers("/backend/login").permitAll()
                .anyRequest().authenticated()
                // 指定未登录入口点
                .and()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                //指定无权限访问点
                .accessDeniedHandler(customAccessDeniedHandler)
                //自定义登录跳转
                .and()
                .formLogin()
                // 设置自定义登录的页面
                //.loginPage("/CRUD/user-login")
                // 登录页表单提交的 action（th:action="@{/my-login}"） URL
                .loginProcessingUrl("/doLogin")
                //自定义登录成功后跳转的url
                //SavedRequestAwareAuthenticationSuccessHandler：默认的成功处理器，默认验证成功后，跳转到原路径
                //.successForwardUrl("/CRUD/get-auth")
                .successHandler(customAuthenticationSuccessHandler)
                //自定义登录失败后跳转的url
                //默认验证失败是使用SimpleUrlAuthenticationFailureHandler
                //.failureForwardUrl("/CRUD/user-fail");
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .csrf().disable()
                // 允许cors跨域请求
                .cors()
                .and()
                // 关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 前置校验token过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
