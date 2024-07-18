package com.ljh.main.ScopeTask.mapper;


import com.ljh.main.ScopeTask.pojo.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskMapper {

    @Insert("insert into task (taskid,scopetype, filetype,content,status) values (#{taskId},#{scopeType}, #{fileType}),#{content}#{status}")
    void addTask(Task task);

    @Select("select * from task where taskid = #{taskId}")
    Task getTaskById(String taskId);


}
