package com.ljh.main.ScopeTask.Service;


import com.ljh.main.ScopeTask.Dto.TaskDto;
import com.ljh.main.ScopeTask.mapper.ResultMapper;
import com.ljh.main.ScopeTask.mapper.TaskMapper;
import com.ljh.main.ScopeTask.pojo.Task;
import org.apache.ibatis.session.RowBounds;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final ResultMapper resultMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    public ResultService(ResultMapper resultMapper) {
        this.resultMapper = resultMapper;
    }









}
