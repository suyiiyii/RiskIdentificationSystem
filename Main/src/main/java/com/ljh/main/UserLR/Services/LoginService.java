package com.ljh.main.UserLR.Services;


import com.ljh.main.UserLR.mapper.UserMapper;
import com.ljh.main.UserLR.pojo.Info;
import com.ljh.main.UserLR.pojo.User;
import com.ljh.main.utils.JWTUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper = new ModelMapper();


    public LoginService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

  /*
    * 登录认证：
    * 使用JWT
    * 1.登录成功后生成token令牌，并返回给客户端，前端拿到令牌后会存储起来
    * 2.在后续的请求中，都会将JWT令牌携带到服务端，
        服务端要进行统一拦截，先校验有没有把令牌携带过来，
        如果没带过来则拒绝访问，如果带过来还要校验一下这个令牌是否有效
        有效则放行去进行请求的处理*/

    public ResponseEntity<?> loginUser(String username, String password) {
        // 检查用户名或密码是否为空 状态码400
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            Info info = new Info();
            info.setMessage("用户名或密码为空");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(info);
        }

        // 用户名或密码错误 状态码400
        User existingUser = userMapper.select(username,password);
        if (existingUser ==null) {
            Info info = new Info();
            info.setMessage("用户名或密码错误");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(info);
        }else{
            // 登录成功 状态码200

            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            String jwt= JWTUtils.generateJWT(claims);
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",jwt);
            map.put("token_type","Bearer");
            map.put("message","登录成功");
            System.out.println("jwt:"+jwt);

            return ResponseEntity.status(HttpStatus.OK).body(map);

        }
    }








}
