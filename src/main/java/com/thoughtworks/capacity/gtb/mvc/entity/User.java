package com.thoughtworks.capacity.gtb.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull(message = "用户名不为空")
    @Length(min = 3, max = 10, message = "用户名不合法，用户名长度应为3到10位")
    @Pattern(regexp = "[a-zA-Z0-9_{3,10}]+", message = "用户名不合法，只能由字母、数字或下划线组成")
    private String username;

    @NotNull(message = "密码是不为空")
    @Length(min = 5, max = 12, message = "密码不合法，密码长度应为5到12位")
    private String password;

    @Email(message = "邮箱不合法")
    private String email;
}
