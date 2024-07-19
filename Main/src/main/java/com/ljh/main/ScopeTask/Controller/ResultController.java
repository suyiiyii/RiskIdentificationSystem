package com.ljh.main.ScopeTask.Controller;


import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.Service.ResultService;
import com.ljh.main.ScopeTask.Service.TaskService;
import com.ljh.main.ScopeTask.mapper.ResultMapper;
import com.ljh.main.ScopeTask.mapper.TaskMapper;
import com.ljh.main.ScopeTask.pojo.Info;
import com.ljh.main.ScopeTask.pojo.Result;
import com.ljh.main.ScopeTask.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class ResultController {
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService)
    {
        this.resultService = resultService;
    }


    @Autowired
    private ResultMapper resultMapper;


    @GetMapping("/result/{taskId}")
    public ResponseEntity<?> getResult(@PathVariable String taskId) {
        Result result = resultMapper.getResultById(taskId);
        if (result == null) {
            Info info = new Info();
            info.setMessage("记录不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(info);


        }
        //记录存在，返回所有信息
        return ResponseEntity.ok(result);

    }




}
