package com.ljh.main.ScopeTask.Service;


import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.mapper.TaskMapper;
import com.ljh.main.ScopeTask.pojo.Task;
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

    public TaskDto getTask(String taskId) {

        return modelMapper.map(taskMapper.getTaskById(taskId), TaskDto.class);
    }


}
