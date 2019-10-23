package com.li.experience.modules.sys.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-06-25 16:15
 **/
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //角色id
    private Long roleid;
    //角色名称
    private String rolename;
    //角色编码
    private String rolecode;
    //序号
    private Long sort;
    //是否删除 0 未删除 1 删除
    private Integer del;
    //状态 0 禁用 1 启用
    private Integer state;
    //角色备注
    private String remark;
    //创建时间
    private Date createdat;
    //创建人
    private String createdby;
    //更新时间
    private Date updatedat;
    //更新人
    private String updatedby;


    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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