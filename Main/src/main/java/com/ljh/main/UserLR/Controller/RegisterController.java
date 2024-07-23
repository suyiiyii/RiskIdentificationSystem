package com.ljh.main.UserLR.Controller;


import com.ljh.main.UserLR.Dto.UserDto;
import com.ljh.main.UserLR.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private final RegisterService registerService;



    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("/user/register")
    public ResponseEntity<?> Register(@RequestBody UserDto user) {
        return registerService.registerUser(user);

    }


}
