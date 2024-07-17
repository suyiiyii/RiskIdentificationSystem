package com.ljh.scopetask.Dto;

import lombok.Data;

@Data
public class TaskDto {
    private int id;
    private String scopeType;
    private String fileType;
    private String textContent;
}
