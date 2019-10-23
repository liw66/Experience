package com.li.experience.common.config;

import com.alibaba.druid.support.json.JSONUtils;
import com.li.experience.common.core.Constant;
import com.li.experience.common.utils.HttpUtils;
import com.li.experience.common.utils.IPUtils;
import com.li.experience.modules.sys.domain.LogDO;
import com.li.experience.modules.sys.domain.UserDO;
import com.li.experience.modules.sys.service.LogService;
import com.li.experience.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.li.experience.common.core.Result.error;

/**
 * @program: experience
 * @description: 全局异常处理
 * @author: Liwei
 * @create: 2019-08-14 14:05
 **/
//@ControllerAdvice
public class BDExceptionHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @ExceptionHandler({Exception.class})
    public Object handlerException(Exception e, HttpServletRequest request) {
        LogDO logDO = new LogDO();
        if (SecurityUtils.getSubject().getPrincipal() != null) {
            String username = SecurityUtils.getSubject().getPrincipal().toString();
            logDO.setUsername(username);
            UserDO userDO = userService.getByName(username);
            logDO.setUserid(userDO.getUserid());
        }
        logDO.setOper(Constant.LOG_EXCEPTION);
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(JSONUtils.toJSONString(HttpUtils.getAllRequestParam(request)));
        logDO.setResult(e.toString());
        logDO.setReqtype(request.getMethod());
        logDO.setLogtype(2);
        logDO.setIp(IPUtils.getIPAddr(request));
        logDO.setDel(0);
        logDO.setCreatedat(new Date());
        logService.save(logDO);
        if(HttpUtils.isAjaxRequest(request)){
            return error(e.getMessage());
        }
        return new ModelAndView("error/500");
    }
}
