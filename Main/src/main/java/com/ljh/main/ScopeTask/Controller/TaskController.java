package com.ljh.main.ScopeTask.Controller;


import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.Service.TaskService;
import com.ljh.main.ScopeTask.mapper.TaskMapper;
import com.ljh.main.ScopeTask.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }


    @PostMapping("/task")
    public ResponseEntity<?> createTask(
            @RequestParam("fileType") String fileType,
            @RequestParam("textContent") String textContent
            /*@RequestPart(value = "file", required = false) MultipartFile file*/) throws IOException {

        try {
            if ("text".equals(fileType)) {

                if (textContent == null || textContent.isEmpty()) {
                    return ResponseEntity.badRequest().body("文本内容不能为空");
                }

                TaskDto taskDto = new TaskDto();
                taskDto.setTaskId(generateTaskID());
                taskDto.setFileType(fileType);
                taskDto.setContent(textContent);
                taskDto.setStatus("排队中");
                taskService.addTask(taskDto);

                Map<String, Object> map = new HashMap<>();
                map.put("message", "提交成功");
                map.put("taskId", generateTaskID());
                return ResponseEntity.ok(map);

            } else if ("audio".equals(fileType)) {
                /*if (file.isEmpty()) {
                    return ResponseEntity.badRequest().body("音频文件不能为空");
                }*/

                TaskDto taskDto = new TaskDto();
                taskDto.setTaskId(generateTaskID());
                taskDto.setFileType(fileType);
                //音频转文本后，再把文本传入数据库
                taskDto.setStatus("排队中");
                taskService.addTask(taskDto);

                Map<String, Object> map = new HashMap<>();
                map.put("message", "提交成功");
                map.put("taskId", generateTaskID());
                return ResponseEntity.ok(map);
            } else {
                return ResponseEntity.badRequest().body("未知的文件类型");
            }
        } catch (Exception e) {
            // 其他异常处理
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误: " + e.getMessage());
        }
    }

    public static String generateTaskID() {
        // 获取当前时间的时间戳
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(formatter);

        // 生成一个0-999之间的随机数，不足3位前面补0
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        String randomNumber = String.format("%03d", randomNum);

        // 拼接前缀、时间戳和随机数
        String taskId = "T" + timestamp + randomNumber;

        return taskId;
    }


    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> getTask(@PathVariable String taskId) {
        Task task = taskMapper.getTaskById(taskId);
        if (task == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("记录不存在");
        }
        //记录存在，返回所有信息
        return ResponseEntity.ok(task);


    }




}
