package com.ljh.userlr.Controller;

import com.ljh.userlr.Dto.UserDto;
import com.ljh.userlr.Services.UserService;
import com.ljh.userlr.mapper.UserMapper;
import com.ljh.userlr.pojo.Info;
import com.ljh.userlr.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    public class BadRequestException extends RuntimeException {
        private int statusCode;

        public BadRequestException(String message, int statusCode) {
            super(message);
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }

    @PostMapping("/user/register")
    public Info Register(@RequestBody UserDto user) {
        try {
            // 检查用户名或密码是否为空 状态码400
            if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
                    user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                throw new BadRequestException("用户名或密码为空", 400);
            }

            // 检查用户名是否已存在 状态码400
            User existingUser = userMapper.selectByUsername(user.getUsername());
            if (existingUser != null) {
                throw new BadRequestException("用户名重复，请使用其他用户名", 400);
            }

            // 注册成功 状态码200
            userService.addUser(user);
            return createInfo("注册成功", 200);

        } catch (BadRequestException e) {
            // 处理状态码为400的异常
            return createInfo(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            // 处理其他异常情况，例如数据库操作失败 状态码500
            return createInfo("服务器内部错误：" + e.getMessage(), 500);
        }
    }

    // 辅助方法，用于创建Info对象并设置状态码
    private Info createInfo(String message, int statusCode) {
        Info info = new Info();
        info.setMessage(message);
        info.setStatusCode(statusCode);
        return info;
    }

    @PostMapping("/user/login")
    public Info Login(@RequestBody UserDto user) {
        try {
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
                userService.addUser(user);
                return createInfo("登录成功", 200);

            }

        } catch (BadRequestException e) {
            // 处理状态码为400的异常
            return createInfo(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            // 处理其他异常情况，例如数据库操作失败 状态码500
            return createInfo("服务器内部错误：" + e.getMessage(), 500);
        }

    }




}
