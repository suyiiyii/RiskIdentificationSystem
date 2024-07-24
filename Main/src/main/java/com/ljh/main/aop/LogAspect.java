package com.ljh.main.aop;

import com.alibaba.fastjson.JSONObject;
import com.ljh.main.ScopeTask.mapper.OperateLogMapper;
import com.ljh.main.ScopeTask.pojo.OperateLog;
import com.ljh.main.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest req;

    @Autowired
    private HttpServletResponse resp;


    @Autowired
    private OperateLogMapper operateLogMapper;


    @Around("@annotation(com.ljh.main.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable{

        //获取当前登录的用户名,即操作人名，operateUser
        String operateUser = JWTUtils.getUsername(req, resp);


        //当前的操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作的类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作的方法名
        String methodName = joinPoint.getSignature().getName();

        // 操作的参数
        StringBuilder methodParamsBuilder = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                methodParamsBuilder.append(", ");
            }
            methodParamsBuilder.append(args[i].toString());
        }

        // 将StringBuilder转换为String
        String methodParams = methodParamsBuilder.toString();

        //原始方法运行开始之前时间
        Long startTime = System.currentTimeMillis();

        //调用原始目标方法运行
        Object result = joinPoint.proceed();

        //原始方法运行之后时间
        Long endTime = System.currentTimeMillis();

        //返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime = endTime - startTime;


        //记录操作日志
        OperateLog operateLog = new OperateLog(operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}", operateLog);

        return result;




    }


}
