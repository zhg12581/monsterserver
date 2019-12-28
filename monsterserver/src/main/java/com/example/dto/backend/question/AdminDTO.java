package com.example.dto.backend.question;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 管理员传输对象
 * @author zhu
 * @version 1.0
 * @date 2019/12/15 下午8:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdminDTO {
    /**管理员账号*/
    @NotBlank(message = "请求参数有误")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,16}$",message = "账号有误，应为3~16位数字和字符组成")
    private String userName;

    /**管理员密码*/
    @NotBlank(message = "请求参数有误")
    @Pattern(regexp = "^[^\\s]{5,18}$", message = "密码长度不符(5~18字符)、包含空或为空")
    private String password;
}
