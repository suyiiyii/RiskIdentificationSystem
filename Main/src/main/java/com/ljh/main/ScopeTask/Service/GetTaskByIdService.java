package com.ljh.main.ScopeTask.Service;


import com.ljh.main.Info;
import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.mapper.TaskMapper;
import com.ljh.main.ScopeTask.pojo.Task;
import com.ljh.main.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.RowBounds;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GetTaskByIdService {
    private final TaskMapper taskMapper;

    public GetTaskByIdService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }



    public ResponseEntity<?> getTask(String taskId, HttpServletRequest req, HttpServletResponse resp) {

        String username= JWTUtils.getUsername(req,resp);
        Task task = taskMapper.getTaskById(taskId,username);
        if (task == null) {
            Info info = new Info();
            info.setMessage("记录不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(info);
        }
        //记录存在，返回所有信息
        return ResponseEntity.ok(task);

    }








}
