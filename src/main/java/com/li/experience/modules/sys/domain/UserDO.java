package com.li.experience.modules.sys.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户信息表
 * 
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-05-09 16:09:52
 */
public class UserDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//用户id
	private Long userid;
	//部门id
	private Long deptid;
	//用户名
	private String username;
	//密码
	private String password;
	//姓名
	private String realname;
	//性别 0 未知的性别 1 男性 2 女性 9 未说明的性别
	private Integer sex;
	//生日
	private Date birthday;
	//电话
	private String moblie;
	//邮箱
	private String email;
	//职务
	private String job;
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
	 * 设置：用户id
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
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
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：姓名
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：性别 0 未知的性别 1 男性 2 女性 9 未说明的性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 0 未知的性别 1 男性 2 女性 9 未说明的性别
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：生日
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：电话
	 */
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	/**
	 * 获取：电话
	 */
	public String getMoblie() {
		return moblie;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：职务
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * 获取：职务
	 */
	public String getJob() {
		return job;
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
