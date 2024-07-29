package com.ljh.main.ScopeTask.Controller;


import com.ljh.main.ScopeTask.Service.GetTaskByIdService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class GetTaskByIdController {
    private final GetTaskByIdService getTaskByIdService;

    @Autowired
    public GetTaskByIdController(GetTaskByIdService getTaskByIdService)
    {
        this.getTaskByIdService = getTaskByIdService;
    }


    //查询单个任务
    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> getTask(@PathVariable String taskId,HttpServletRequest req, HttpServletResponse resp) {
       return getTaskByIdService.getTask(taskId, req, resp);

    }






}


