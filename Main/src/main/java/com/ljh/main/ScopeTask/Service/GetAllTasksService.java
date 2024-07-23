package com.ljh.main.ScopeTask.Service;


import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.mapper.TaskMapper;
import com.ljh.main.ScopeTask.pojo.Task;
import com.ljh.main.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.RowBounds;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class GetAllTasksService {
    private final TaskMapper taskMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    public GetAllTasksService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    //用户查询所有任务，只能查到自己创建的所有任务，任务表中有用户名的字段以供标识。
    //用户登录后，发送查询所有任务的请求时，根据token令牌识别该用户，根据对应的用户名返回所有其创建的任务信息

    public List<TaskDto> allTasks(HttpServletRequest req, HttpServletResponse resp, int page, int size) {
        String username= JWTUtils.getUsername(req,resp);

        return getAllTasks(username,page, size);
    }

    public List<TaskDto> getAllTasks(String username, int page, int size) {
        int offset = (page - 1) * size;
        List<Task> tasks = taskMapper.getAllTasks(username,new RowBounds(offset, size));
        return tasks.stream().map(grade -> modelMapper.map(grade, TaskDto.class)).toList();

    }






}
