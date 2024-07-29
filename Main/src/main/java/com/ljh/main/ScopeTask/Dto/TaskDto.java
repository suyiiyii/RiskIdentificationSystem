package com.ljh.main.ScopeTask.Dto;

import lombok.Data;

@Data
public class TaskDto {
    private int id;
    private String taskId;
    private String scopeType;
    private String fileType;
    private String Content;
    private String status;
    private String resultId;
    private String username;
}
