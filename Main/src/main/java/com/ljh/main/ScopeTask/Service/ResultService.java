package com.ljh.main.ScopeTask.Service;


import com.ljh.main.ScopeTask.mapper.ResultMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private final ResultMapper resultMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    public ResultService(ResultMapper resultMapper) {
        this.resultMapper = resultMapper;
    }









}
