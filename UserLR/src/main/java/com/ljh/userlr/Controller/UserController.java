package com.ljh.userlr.Controller;

import com.ljh.userlr.Dto.UserDto;
import com.ljh.userlr.Services.UserService;
import com.ljh.userlr.mapper.UserMapper;
import com.ljh.userlr.pojo.Info_register;
import com.ljh.userlr.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;


    @Autowired
    private  UserMapper userMapper;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/register")
    public  ResponseEntity<String> Register(@RequestBody UserDto user) {

            // 检查用户名或密码是否为空 状态码400
            if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
                    user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户名或密码为空");
            }

            // 检查用户名是否已存在 状态码400
            User existingUser = userMapper.selectByUsername(user.getUsername());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户名重复，请使用其他用户名");

            }

            // 注册成功 状态码200
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("注册成功");
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");


    }

    // 辅助方法，用于创建Info对象并设置状态码
    private Info_register createInfo(String message) {
        Info_register info = new Info_register();
        info.setMessage(message);
        return info;
    }


   /* @PostMapping("/user/login")
    public Info_register Login(@RequestBody UserDto user) {
        *//*try {
            // 检查用户名或密码是否为空 状态码400
            if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
                    user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                throw new BadRequestException("用户名或密码为空", 400);
            }

            // 用户名或密码错误 状态码400
            User existingUser = userMapper.select(user.getUsername(),user.getPassword());
            if (existingUser ==null) {
                throw new BadRequestException("用户名或密码错误", 400);
            }else{
                // 登录成功 状态码200
                Info_register info = new Info_register();
                *//**//*String accessToken = retrieveAccessTokenFromSomewhere(); // 定义一个方法来获取access_token
                info.setAccess_token(accessToken);*//**//*
                *//**//*info.setToken_type("Bearer");*//**//*
                info.setStatusCode(200);
                info.setMessage("登录成功");
                return info;

            }

        } catch (BadRequestException e) {
            // 处理状态码为400的异常
            return createInfo(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            // 处理其他异常情况，例如数据库操作失败 状态码500
            return createInfo("服务器内部错误：" + e.getMessage(), 500);
        }*//*

    }
*/



}
