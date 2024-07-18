package com.ljh.main.UserLR.Services;


import com.ljh.main.UserLR.Dto.UserDto;
import com.ljh.main.UserLR.mapper.UserMapper;
import com.ljh.main.UserLR.pojo.User;
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








}
