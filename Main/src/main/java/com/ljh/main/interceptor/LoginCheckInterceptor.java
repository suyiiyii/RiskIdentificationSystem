package com.ljh.main.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ljh.main.Info;
import com.ljh.main.UserLR.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//在目标资源方法运行前运行，返回true：放行，返回false：不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //1.获取请求Url
        String url=req.getRequestURL().toString();
        log.info("请求的url：{}",url);

        //2.判断请求url中是否包含login，如果包含，放行
        if(url.contains("login")){
            log.info("登录操作，放行");
            return true;
        }

        //3.获取请求头中的令牌（token）
        String authHeader=req.getHeader("Authorization");
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
            resp.getWriter().write(notLogin);
            return false;
        }
        //5.解析token，如果解析失败返回错误结果（未登录）
        try {
            JWTUtils.parseJWT(jwt);
        } catch (Exception e){//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录的错误信息");

            Info info=new Info();
            info.setMessage("未登录");
            Gson gson = new Gson();
            String jsonInfo = gson.toJson(info);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonInfo);

            return false;
        }

        //6.放行
        log.info("令牌合法，放行");
        return true;


    }

    @Override//在目标资源方法(Controller方法）运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle运行了");
    }

    @Override//视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion运行了");
    }
}
