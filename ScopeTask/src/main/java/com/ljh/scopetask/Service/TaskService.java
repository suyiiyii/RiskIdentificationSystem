package com.ljh.scopetask.Service;

import com.ljh.scopetask.Dto.TaskDto;
import com.ljh.scopetask.mapper.TaskMapper;
import com.ljh.scopetask.pojo.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskMapper taskMapper;
    private final ModelMapper modelMapper = new ModelMapper();
    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    public TaskDto addTask(TaskDto task) {
        taskMapper.addTask(modelMapper.map(task, Task.class));
        return task;
    }
}
