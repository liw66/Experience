package com.li.experience.modules.sys.dao;

import com.li.experience.modules.sys.domain.DeptDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Liwei
 * @email test@163.com
 * @date 2019-05-09 16:09:52
 */
@Mapper
public interface DeptDao {

	DeptDO get(Long deptid);
	
	List<DeptDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(Long deptid);

}
