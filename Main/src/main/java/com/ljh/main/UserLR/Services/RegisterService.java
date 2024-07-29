package com.ljh.main.UserLR.Services;


import com.google.gson.Gson;
import com.ljh.main.UserLR.Dto.UserDto;
import com.ljh.main.UserLR.mapper.UserMapper;
import com.ljh.main.UserLR.pojo.Info;
import com.ljh.main.UserLR.pojo.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private Gson gson;


    public RegisterService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDto addUser(UserDto user) {
        userMapper.addUser(modelMapper.map(user, User.class));
        return user;
    }

    public ResponseEntity<?> registerUser(UserDto user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
                user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            /*Info info = new Info();
            info.setMessage("用户名或密码为空");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(info);*/
            String json = gson.toJson("用户名或密码为空");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        // 检查用户名是否已存在 状态码400
        User existingUser = userMapper.selectByUsername(user.getUsername());
        if (existingUser != null) {
           /* Info info = new Info();
            info.setMessage("用户名重复，请使用其他用户名");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(info);*/
            String json = gson.toJson("用户名重复，请使用其他用户名");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);

        }

        // 注册成功 状态码200
        addUser(user);
        /*Info info = new Info();
        info.setMessage("注册成功");
        return ResponseEntity.status(HttpStatus.OK).body(info);*/
        String json = gson.toJson("注册成功");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }








}
