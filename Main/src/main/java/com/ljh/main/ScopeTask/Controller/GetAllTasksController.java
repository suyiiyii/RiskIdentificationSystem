package com.ljh.main.ScopeTask.Controller;


import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.Service.GetAllTasksService;
import com.ljh.main.anno.Log;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class GetAllTasksController {
    private final GetAllTasksService getAllTasksService;

    @Autowired
    public GetAllTasksController(GetAllTasksService getAllTasksService)
    {
        this.getAllTasksService = getAllTasksService;
    }


    //用户查询所有任务，只能查到自己创建的所有任务，任务表中有用户名的字段以供标识。
    //用户登录后，发送查询所有任务的请求时，根据token令牌识别该用户，根据对应的用户名返回所有其创建的任务信息
    @Log
    @GetMapping("/task")
    List<TaskDto> Tasks(HttpServletRequest req, HttpServletResponse resp, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        return getAllTasksService.allTasks(req,resp,page,size);
    }






}


