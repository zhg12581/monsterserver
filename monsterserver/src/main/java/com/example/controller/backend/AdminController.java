package com.example.controller.backend;

import com.example.common.AdminStatusCodeEnum;
import com.example.common.ResponseResult;
import com.example.dto.backend.question.AdminDTO;
import com.example.entity.AdminDO;
import com.example.service.impl.AdminServiceImpl;
import com.example.util.DateTimeUtils;
import com.example.util.HttpUtils;
import com.example.util.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 后台用户行为控制类
 * @author zhu
 * @version 1.0
 * @date 2019/12/14 下午10:03
 */
@Controller
@RequestMapping("/backend")
@SessionAttributes(value = {"admin", "lastLoginIpAddr", "lastLoginTime"})
@Validated
@Slf4j
public class AdminController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    /**
     * 用户服务类对象
     */
    @Autowired
    private AdminServiceImpl adminService;

    /**
     * 密码加密工具对象
     */
    private PasswordEncoder passwordEncoder;

    /**
     * 执行用户登录操作，将验证用户信息是否正确，并返回相应判断结果
     *
     * @param adminDTO 所需验证的用户对象
     * @return java.lang.String
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseResult login(@RequestBody(required = false) @Valid @NotNull(message = "请求参数不能为空") AdminDTO adminDTO, ModelMap model, HttpServletRequest request, HttpSession session) {
        if (adminService.verification(adminService.transformDTO(adminDTO))) {
            AdminDO adminDO = adminService.getAdminByUserName(adminDTO.getUserName());
            if (adminDO.getStatus().equals(AdminStatusCodeEnum.LOCKING.getCode())) {
                return ResponseResult.error("登录失败，账号处于冻结状态，请联系相关管理员");
            }
            String ip = adminDO.getLoginIpAddr();
            String time = DateTimeUtils.dateToStr(adminDO.getLoginTime());
            model.addAttribute("lastLoginIpAddr", StringUtils.isNotBlank(ip) ? ip : "无")
                    .addAttribute("lastLoginTime", StringUtils.isNotBlank(time) ? time : "无");
            adminDO.setLoginIpAddr(HttpUtils.getIpAddress(request))
                    .setLoginTime(new Date());
            adminService.saveOrUpdate(adminDO);
            log.info("已记录账号：{}——登录IP：{}——登录时间：{}", adminDO.getName(), adminDO.getLoginIpAddr(), adminDO.getLoginTime());
            model.addAttribute("admin", adminDO);
            return ResponseResult.success("登录成功");
        }
        return ResponseResult.error("登录失败，用户名或密码有误");
    }

    @GetMapping(value = "/hello")
    @ResponseBody
    public String play() {
        return "hello";
    }


   /* @PostMapping("/LoginIn")
    @ResponseBody
    public String LoginIn(@RequestBody(required = false) @Valid @NotNull(message = "请求参数不能为空") AdminDTO adminDTO, HttpServletRequest request){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(adminDTO.getUserName());
        final String token = jwtTokenUtils.generateToken(userDetails);
        return token;
    }*/

    @PostMapping("/see")
    @ResponseBody
    public String see(@RequestBody AdminDTO adminDTO){
        //String username=jwtTokenUtils.getUsernameFromToken(token);
        return "123";
    }
}
