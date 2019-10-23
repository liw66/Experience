package com.li.experience.modules.sys.domain;

import java.io.Serializable;
import java.util.Date;
import java.math.BigInteger;

/**
 * 系统日志表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-08-08 15:40:00
 */
public class LogDO implements Serializable {

    private static final long serialVersionUID = 1L;


    //日志id
    private Long logid;
    //用户id
    private Long userid;
    //用户名
    private String username;
    //用户操作
    private String oper;
    //请求方法
    private String method;
    //请求参数
    private String params;
    //返回值
    private String result;
    //请求类型 GET POST
    private String reqtype;
    //日志类型 0 登录 1 操作 2 异常
    private Integer logtype;
    //请求ip
    private String ip;
    //响应时间
    private Long time;
    //是否删除 0 未删除 1 删除
    private Integer del;
    //创建时间
    private Date createdat;


    /**
     * 获取日志id
     */
    public Long getLogid() {
        return logid;
    }

    /**
     * 设置日志id
     */
    public void setLogid(Long logid) {
        this.logid = logid;
    }

    /**
     * 获取用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取用户操作
     */
    public String getOper() {
        return oper;
    }

    /**
     * 设置用户操作
     */
    public void setOper(String oper) {
        this.oper = oper;
    }

    /**
     * 获取请求方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置请求方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取请求参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置请求参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取返回值
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置返回值
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取请求类型 GET POST
     */
    public String getReqtype() {
        return reqtype;
    }

    /**
     * 设置请求类型 GET POST
     */
    public void setReqtype(String reqtype) {
        this.reqtype = reqtype;
    }

    /**
     * 获取日志类型 0 登录 1 操作 2 异常
     */
    public Integer getLogtype() {
        return logtype;
    }

    /**
     * 设置日志类型 0 登录 1 操作 2 异常
     */
    public void setLogtype(Integer logtype) {
        this.logtype = logtype;
    }

    /**
     * 获取请求ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置请求ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取响应时间
     */
    public Long getTime() {
        return time;
    }

    /**
     * 设置响应时间
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * 获取是否删除 0 未删除 1 删除
     */
    public Integer getDel() {
        return del;
    }

    /**
     * 设置是否删除 0 未删除 1 删除
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * 获取创建时间
     */
    public Date getCreatedat() {
        return createdat;
    }

    /**
     * 设置创建时间
     */
    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

}