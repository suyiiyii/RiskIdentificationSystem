package com.ljh.userlr.mapper;

import com.ljh.userlr.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into tb_user (username, password) values (#{username}, #{password})")
    void addUser(User user);

}
