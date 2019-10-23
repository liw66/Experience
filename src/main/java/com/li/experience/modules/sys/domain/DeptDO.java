package com.li.experience.modules.sys.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-05-09 16:09:52
 */
public class DeptDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//部门id
	private Long deptid;
	//上级id
	private Long parentid;
	//部门名称
	private String deptname;
	//序号
	private Long sort;
	//是否删除 0 否 1 是
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

	/**
	 * 设置：部门id
	 */
	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	/**
	 * 获取：部门id
	 */
	public Long getDeptid() {
		return deptid;
	}
	/**
	 * 设置：上级id
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	/**
	 * 获取：上级id
	 */
	public Long getParentid() {
		return parentid;
	}
	/**
	 * 设置：部门名称
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	/**
	 * 获取：部门名称
	 */
	public String getDeptname() {
		return deptname;
	}
	/**
	 * 设置：序号
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}
	/**
	 * 获取：序号
	 */
	public Long getSort() {
		return sort;
	}
	/**
	 * 设置：是否删除 0 否 1 是
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
	/**
	 * 获取：是否删除 0 否 1 是
	 */
	public Integer getDel() {
		return del;
	}
	/**
	 * 设置：状态 0 禁用 1 启用
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态 0 禁用 1 启用
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatedat() {
		return createdat;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreatedby() {
		return createdby;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdatedat() {
		return updatedat;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdatedby() {
		return updatedby;
	}
}
