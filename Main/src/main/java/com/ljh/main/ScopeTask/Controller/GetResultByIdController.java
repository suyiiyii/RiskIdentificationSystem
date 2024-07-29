package com.ljh.main.ScopeTask.Controller;


import com.ljh.main.ScopeTask.Service.GetResultByIdService;
import com.ljh.main.ScopeTask.mapper.ResultMapper;
import com.ljh.main.Info;
import com.ljh.main.ScopeTask.pojo.Result;
import com.ljh.main.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetResultByIdController {
    private final GetResultByIdService resultService;

    @Autowired
    public GetResultByIdController(GetResultByIdService resultService)
    {
        this.resultService = resultService;
    }



    @GetMapping("/result/{resultId}")
    public ResponseEntity<?> getResult(@PathVariable String resultId, HttpServletRequest req, HttpServletResponse resp) {
       return resultService.getResult(resultId,req,resp);

    }




}
