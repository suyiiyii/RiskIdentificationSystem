package com.ljh.main.ScopeTask.Service;


import com.ljh.main.Info;
import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.mapper.TaskMapper;
import com.ljh.main.ScopeTask.pojo.Task;
import com.ljh.main.utils.GenerateIdUtils;
import com.ljh.main.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateAudioTaskService {
    private final TaskMapper taskMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    public CreateAudioTaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    public TaskDto addTask(TaskDto task) {
        taskMapper.addTask(modelMapper.map(task, Task.class));
        return task;
    }


    public ResponseEntity<?> createAudioTask(String scopeType, String fileType,
            MultipartFile file , HttpServletRequest req, HttpServletResponse resp)  {
        System.out.println("进来了2");

        try {

            if ("audio".equals(fileType)) {
                if (file.isEmpty()) {
                    return ResponseEntity.badRequest().body("音频文件不能为空");
                }



                TaskDto taskDto = new TaskDto();
                taskDto.setTaskId(GenerateIdUtils.generateTaskID());
                taskDto.setScopeType(scopeType);
                taskDto.setFileType(fileType);
                taskDto.setContent("audio文件");
                taskDto.setStatus("排队中");
                //taskDto.setResultId(generateResultID());
                String username = JWTUtils.getUsername(req, resp);
                taskDto.setUsername(username);


                addTask(taskDto);
                //System.out.println("加进了");

                Map<String, Object> map = new HashMap<>();
                map.put("message", "提交成功");
                map.put("taskId", taskDto.getTaskId());
                saveFileToLocal(file);
                return ResponseEntity.ok(map);
            }

            else {
                System.out.println(fileType);
                Info info = new Info();
                info.setMessage("暂不支持其他文件格式");
                return ResponseEntity.badRequest().body(info);
            }
        } catch (Exception e) {
            Info info = new Info();
            info.setMessage("服务器错误");
            // 其他异常处理
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(info);
        }
    }


    public void saveFileToLocal(MultipartFile file) {
        String targetDir = "C:\\code";
        Path path = Paths.get(targetDir);

        // Create directory if it does not exist
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create directory: " + targetDir, e);
            }
        }

        // Build the full path for the file
        Path fullPath = path.resolve(file.getOriginalFilename());

        try {
            // Write the file content to the specified path
            file.transferTo(fullPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file to local", e);
        }
    }






}
