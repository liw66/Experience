package com.li.experience.modules.sys.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-05-23 14:52
 **/
public class UserAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "登录名", width = 20)
    private String username;

    @Excel(name = "姓名", width = 20)
    private String realname;

    @Excel(name = "性别", replace = {"未知的性别_0","男性_1", "女性_2","未说明的性别_9"},width = 20)
    private int sex;

    @Excel(name = "电话", width = 20)
    private String moblie;

    @Excel(name = "邮箱", width = 20)
    private String email;

    @Excel(name = "状态", replace = {"禁用_0","启用_1"}, width = 20)
    private int state;

    @Excel(name = "创建时间", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd",width = 20)
    private Date createdat;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }
}
