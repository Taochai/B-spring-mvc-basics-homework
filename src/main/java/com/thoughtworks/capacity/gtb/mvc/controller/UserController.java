package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.entity.User;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@Validated
public class UserController {

    UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @PostMapping(path = "/register")
    public void registerUser(@RequestBody @Valid User user) {
        userService.registerUser(user);
    }

    @GetMapping(path = "/login")
    public User getUserByNameAndPassword(@RequestParam(name = "username") @Length(min = 3, max = 10, message = "用户名长度应为3到10位") @Pattern(regexp = "[a-zA-Z0-9_{3,10}]+",message = "用户名不合法") String username,
                                         @RequestParam(name = "password") @Length(min = 5, max = 12, message = "密码长度应为5到12位") String password) {
        return userService.getUserByNameAndPassword(username,password);
    }

}
