package com.ljh.scopetask.mapper;

import com.ljh.scopetask.pojo.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    @Insert("insert into task (scopetype, filetype,textconent) values (#{scopeType}, #{fileType}),#{textconent}")
    void addTask(Task task);


}
