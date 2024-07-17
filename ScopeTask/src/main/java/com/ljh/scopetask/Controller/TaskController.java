package com.ljh.scopetask.Controller;

import com.ljh.scopetask.Dto.TaskDto;
import com.ljh.scopetask.Service.TaskService;
import com.ljh.scopetask.mapper.TaskMapper;
import com.ljh.scopetask.pojo.Info;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    public class BadRequestException extends RuntimeException {
        private int statusCode;

        public BadRequestException(String message, int statusCode) {
            super(message);
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }

    @PostMapping("/user/task")
    public Info createTask(@RequestBody TaskDto task)
    {


        try {

            if (!"risk".equals(task.getScopeType())) {
                throw new BadRequestException("参数scopeType错误", 400);
            }

            if (!"text".equals(task.getFileType()) || !"audio".equals(task.getFileType())) {
                throw new BadRequestException("参数fileType错误", 400);
            }


            // 创建成功 状态码200
            TaskDto taskDto = taskService.addTask(task);
            Info info= new Info();
            info.setMessage("提交成功");
            info.setTaskId(taskDto.getId());
            info.setStatusCode(200);
            return info;


        } catch (BadRequestException e) {
            // 处理状态码为400的异常
            return createInfo(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            return createInfo("服务器错误：" + e.getMessage(), 500);
        }
    }

    private Info createInfo(String message,int statusCode) {
        Info info = new Info();
        info.setMessage(message);
        info.setStatusCode(statusCode);
        return info;
    }


}
