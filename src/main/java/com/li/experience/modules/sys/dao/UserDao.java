package com.li.experience.modules.sys.dao;

import com.li.experience.modules.sys.domain.UserDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.li.experience.modules.sys.domain.UserAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息
 * @author Liwei
 * @email test@163.com
 * @date 2019-05-09 16:09:52
 */
@Mapper
public interface UserDao {

	Set findRoles(String username);

	Set findPermissions(String username);

	UserDO get(Long userid);

	UserDO getByName(String username);
	
	List<UserDO> list(Map<String,Object> map);

	List<UserAO> export(Map<String, Object> map);

	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userid);
	
	int batchRemove(Long[] userids);

	List getRole(Long userid);

	int saveRole(List list);

	int removeRole(Long userid);
}
