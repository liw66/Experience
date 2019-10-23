package com.li.experience.common.aop;

import com.alibaba.druid.support.json.JSONUtils;
import com.li.experience.common.utils.HttpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: experience
 * @description: WEB日志打印
 * @author: Liwei
 * @create: 2019-08-13 09:58
 **/
@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution( * com.li.experience..controller.*.*(..))")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint point)  {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) point.getSignature();
        logger.info("请求地址：" + request.getRequestURL().toString());
        logger.info("HTTP METHOD：" + request.getMethod());
        logger.info("CLASS METHOD：" + signature.getDeclaringTypeName() + "." + signature.getName());
        logger.info("参数：" + JSONUtils.toJSONString(HttpUtils.getAllRequestParam(request)));
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long time = System.currentTimeMillis() - startTime;
        logger.info("耗时：" + time);
        return obj;
    }

    @AfterReturning(returning = "obj",pointcut = "logPointCut()")
    public void doAfterReturning(Object obj)  {
        logger.info("返回值：" + obj);
    }
}
