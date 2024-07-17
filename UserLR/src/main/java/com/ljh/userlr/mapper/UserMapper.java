package com.ljh.userlr.mapper;

import com.ljh.userlr.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into tb_user (username, password) values (#{username}, #{password})")
    void addUser(User user);


    @Select({"select * from tb_user where username=#{username}"})
    User selectByUsername(String username);

    @Select({"select * from tb_user where username=#{username} and password=#{password}"})
    User select(@Param("username") String username, @Param("password") String password);


}
