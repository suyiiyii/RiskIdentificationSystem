package com.ljh.userlr.Services;

import com.ljh.userlr.Dto.UserDto;
import com.ljh.userlr.mapper.UserMapper;
import com.ljh.userlr.pojo.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper = new ModelMapper();


    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDto addUser(UserDto user) {
        userMapper.addUser(modelMapper.map(user, User.class));
        return user;
    }

    public UserDto select(UserDto user) {
        return modelMapper.map(userMapper.select(user.getUsername(), user.getPassword()), UserDto.class);
    }












}
