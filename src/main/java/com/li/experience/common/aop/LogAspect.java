package com.li.experience.common.aop;

import com.alibaba.druid.support.json.JSONUtils;
import com.li.experience.common.core.Constant;
import com.li.experience.common.utils.HttpUtils;
import com.li.experience.common.utils.IPUtils;
import com.li.experience.modules.sys.domain.LogDO;
import com.li.experience.modules.sys.domain.UserDO;
import com.li.experience.modules.sys.service.LogService;
import com.li.experience.modules.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @program: experience
 * @description: 日志注解实现
 * @author: Liwei
 * @create: 2019-08-08 16:28
 **/
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    private LogDO logDO = new LogDO();

    @Pointcut("@annotation(com.li.experience.common.aop.Log)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint point){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        if (SecurityUtils.getSubject().getPrincipal() != null) {
            String username = SecurityUtils.getSubject().getPrincipal().toString();
            logDO.setUsername(username);
            UserDO userDO = userService.getByName(username);
            logDO.setUserid(userDO.getUserid());
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            logDO.setOper(syslog.value());
            if (StringUtils.equals(syslog.value(),"登录")){
                logDO.setLogtype(0);
            }else{
                logDO.setLogtype(1);
            }
        }
        logDO.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        logDO.setParams(JSONUtils.toJSONString(HttpUtils.getAllRequestParam(request)));
        logDO.setReqtype(request.getMethod());
        logDO.setIp(IPUtils.getIPAddr(request));
        logDO.setDel(0);
        logDO.setCreatedat(new Date());
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object obj = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        logDO.setTime(time);
        return obj;
    }

    @AfterReturning(returning = "obj", pointcut = "logPointCut()")
    public void doAfterReturning(Object obj){
        logDO.setResult(obj.toString());
        logService.save(logDO);
    }

    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(Throwable e){
        LogDO logDO = new LogDO();
        if (SecurityUtils.getSubject().getPrincipal() != null) {
            String username = SecurityUtils.getSubject().getPrincipal().toString();
            logDO.setUsername(username);
            UserDO userDO = userService.getByName(username);
            logDO.setUserid(userDO.getUserid());
        }
        logDO.setOper(Constant.LOG_ERROR);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(JSONUtils.toJSONString(HttpUtils.getAllRequestParam(request)));
        logDO.setResult(e.toString());
        logDO.setReqtype(request.getMethod());
        logDO.setLogtype(2);
        logDO.setIp(IPUtils.getIPAddr(request));
        logDO.setDel(0);
        logDO.setCreatedat(new Date());
        logService.save(logDO);
    }

}
