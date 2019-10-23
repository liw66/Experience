package com.li.experience.modules.sys.domain;

import java.io.Serializable;
import java.util.Date;
import java.math.BigInteger;

/**
 * 字典信息表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-08-16 11:15:38
 */
public class DictDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //字典id
    private Long dictid;
    //数据名
    private String name;
    //数据值
    private String value;
    //类型
    private String type;
    //类型描述
    private String desc;
    //备注
    private String remark;
    //序号
    private Long sort;
    //是否删除 0 否 1 是
    private Integer del;
    //创建时间
    private Date createdat;
    //创建人
    private String createdby;
    //更新时间
    private Date updatedat;
    //更新人
    private String updatedby;


    /**
     * 获取字典id
     */
    public Long getDictid() {
        return dictid;
    }

    /**
     * 设置字典id
     */
    public void setDictid(Long dictid) {
        this.dictid = dictid;
    }

    /**
     * 获取数据名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置数据名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取数据值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置数据值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取类型描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置类型描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取序号
     */
    public Long getSort() {
        return sort;
    }

    /**
     * 设置序号
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 获取是否删除 0 否 1 是
     */
    public Integer getDel() {
        return del;
    }

    /**
     * 设置是否删除 0 否 1 是
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

    /**
     * 获取创建人
     */
    public String getCreatedby() {
        return createdby;
    }

    /**
     * 设置创建人
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    /**
     * 获取更新时间
     */
    public Date getUpdatedat() {
        return updatedat;
    }

    /**
     * 设置更新时间
     */
    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    /**
     * 获取更新人
     */
    public String getUpdatedby() {
        return updatedby;
    }

    /**
     * 设置更新人
     */
    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

}