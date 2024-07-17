package com.ljh.scopetask.pojo;

import lombok.Data;

@Data
public class Task {
    private int id;
    private String scopeType;
    private String fileType;
    private String textContent;
}
