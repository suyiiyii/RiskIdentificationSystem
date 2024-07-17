package com.ljh.userlr.Controller;

import com.ljh.userlr.Dto.UserDto;
import com.ljh.userlr.Services.UserService;
import com.ljh.userlr.pojo.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Info addUser(@RequestBody UserDto user) {
        userService.addUser(user);
        Info info = new Info();
        info.setMessage("注册成功");
        return info;

    }


}
