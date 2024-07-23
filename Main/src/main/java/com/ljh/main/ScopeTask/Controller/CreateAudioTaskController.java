package com.ljh.main.ScopeTask.Controller;


import com.ljh.main.Info;
import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.Service.CreateAudioTaskService;
import com.ljh.main.ScopeTask.pojo.Task;
import com.ljh.main.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/")
public class CreateAudioTaskController {
    private final CreateAudioTaskService createAudioTaskService;

    @Autowired
    public CreateAudioTaskController(CreateAudioTaskService createAudioTaskService)
    {
        this.createAudioTaskService = createAudioTaskService;
    }
    

    @PostMapping("/task_audio")
    public ResponseEntity<?> createAudioTask(
            @RequestParam("scopeType") String scopeType,
            @RequestParam("fileType") String fileType,
            @RequestParam("fileContent") MultipartFile file , HttpServletRequest req, HttpServletResponse resp)  {
        System.out.println("进来了2");

        return createAudioTaskService.createAudioTask(scopeType,fileType,file,req,resp);
    }









}


