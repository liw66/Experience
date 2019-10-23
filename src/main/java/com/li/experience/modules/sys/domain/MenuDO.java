package com.li.experience.modules.sys.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-06-25 14:12
 **/
public class MenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //菜单id
    private Long menuid;
    //上级id
    private Long parentid;
    //菜单名称
    private String menuname;
    //菜单地址
    private String url;
    //权限标志
    private String perms;
    //菜单图标
    private String icon;
    //菜单类型 0 目录 1 菜单 2 按钮
    private Integer type;
    //序号
    private Long sort;
    //是否删除 0 未删除 1 删除
    private Integer del;
    //状态 0 禁用 1 启用
    private Integer state;
    //创建时间
    private Date createdat;
    //创建人
    private String createdby;
    //更新时间
    private Date updatedat;
    //更新人
    private String updatedby;

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }
}