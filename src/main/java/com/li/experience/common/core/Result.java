package com.li.experience.common.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-04-24 14:18
 **/
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Result<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(){
    }

    public Result(T data){
        this();
        this.data = data;
    }

    public Result(int code, String msg){
        this();
        this.code = code;
        this.msg = msg;
    }
    public Result(int code, String msg, T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static Result ok(){
        Result result = new Result(0,"ok");
        return result;
    }

    public static Result ok(String msg){
        Result result = new Result(0,msg);
        return result;
    }

    public static Result error(){
        Result result = new Result(1,"error");
        return result;
    }

    public static Result error(String msg){
        Result result = new Result(1,msg);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
