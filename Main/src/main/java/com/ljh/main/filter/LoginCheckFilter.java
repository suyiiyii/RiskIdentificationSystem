package com.ljh.main.filter;

import com.alibaba.fastjson.JSONObject;
import com.ljh.main.Info;
import com.ljh.main.UserLR.utils.JWTUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求Url
        String url=request.getRequestURL().toString();
        log.info("请求的url：{}",url);

        //2.判断请求url中是否包含login，如果包含，放行
        if(url.contains("login")){
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //3.获取请求头中的令牌（token）
        String authHeader=request.getHeader("Authorization");
        System.out.println("请求头authHeader："+authHeader);

        String jwt = authHeader.substring(7); // 移除前7个字符，即"Bearer "
        // 现在jwt变量包含了不带前缀的令牌
        System.out.println("jwt:"+jwt);

        //4.判断令牌是否存在，如果存在，放行
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Info info=new Info();
            info.setMessage("未登录");
            String notLogin= JSONObject.toJSONString(info);
            response.getWriter().write(notLogin);
            return;
        }
        //5.解析token，如果解析失败返回错误结果（未登录）
        try {
            JWTUtils.parseJWT(jwt);
        } catch (Exception e){//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录的错误信息");

            Info info=new Info();
            info.setMessage("未登录");
            String notLogin= JSONObject.toJSONString(info);
            response.getWriter().write(notLogin);
            return;
        }

        //6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
