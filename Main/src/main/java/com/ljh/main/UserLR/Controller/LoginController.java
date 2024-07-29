package com.ljh.main.UserLR.Controller;


import com.google.gson.Gson;
import com.ljh.main.UserLR.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class LoginController {
    private final LoginService loginService;



    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    /*
    * 登录认证：
    * 使用JWT
    * 1.登录成功后生成token令牌，并返回给客户端，前端拿到令牌后会存储起来
    * 2.在后续的请求中，都会将JWT令牌携带到服务端，
        服务端要进行统一拦截，先校验有没有把令牌携带过来，
        如果没带过来则拒绝访问，如果带过来还要校验一下这个令牌是否有效
        有效则放行去进行请求的处理*/



    @PostMapping("/user/login")
    public ResponseEntity<?> Login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

          return loginService.loginUser(username,password);


        }


        }






