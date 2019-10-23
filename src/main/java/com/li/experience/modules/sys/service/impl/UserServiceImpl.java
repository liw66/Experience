package com.li.experience.modules.sys.service.impl;

import com.li.experience.modules.sys.dao.UserDao;
import com.li.experience.modules.sys.domain.UserAO;
import com.li.experience.modules.sys.domain.UserDO;
import com.li.experience.modules.sys.domain.UserVO;
import com.li.experience.modules.sys.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public Set findRoles(String username) {
		return userDao.findRoles(username);
	}

	@Override
	public Set findPermissions(String username) {
		return userDao.findPermissions(username);
	}
	
	@Override
	public UserVO get(Long userid){
		UserVO userVO = new UserVO();
		List<Long> roleids = userDao.getRole(userid);
		userVO.setRoleids(roleids);
		UserDO userDO = userDao.get(userid);
		BeanUtils.copyProperties(userDO,userVO);
		return userVO;
	}

	@Override
	public UserDO getByName(String username){
		return userDao.getByName(username);
	}

	@Override
	public List<UserDO> list(Map<String, Object> map){
		return userDao.list(map);
	}

	@Override
	public List<UserAO> export(Map<String, Object> map) {
		return userDao.export(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UserVO userVO){
		UserDO userDO = new UserDO();
		String[] ignoreProperties = new String[]{"roleids"};
		BeanUtils.copyProperties(userVO,userDO,ignoreProperties);
		int val = userDao.save(userDO);
		if (val > 0){
			List<Long> roleids = userVO.getRoleids();
			List list = new ArrayList();
			for (Long roleid : roleids){
				Map map = new HashMap();
				map.put("userid",userVO.getUserid());
				map.put("roleid",roleid);
				list.add(map);
			}
			if (list.size() > 0){
				userDao.saveRole(list);
			}
		}
		return val;
	}
	
	@Override
	public int update(UserVO userVO){
		UserDO userDO = new UserDO();
		String[] ignoreProperties = new String[]{"roleids"};
		BeanUtils.copyProperties(userVO,userDO,ignoreProperties);
		int val = userDao.update(userDO);
		if (val > 0){
			userDao.removeRole(userVO.getUserid());
			List<Long> roleids = userVO.getRoleids();
			List list = new ArrayList();
			for (Long roleid : roleids){
				Map map = new HashMap();
				map.put("userid",userVO.getUserid());
				map.put("roleid",roleid);
				list.add(map);
			}
			if (list.size() > 0){
				userDao.saveRole(list);
			}
		}
		return val;
	}
	
	@Override
	public int remove(Long userid){
		int val = userDao.remove(userid);
		if (val > 0){
			userDao.removeRole(userid);
		}
		return val;
	}
	
	@Override
	public int batchRemove(Long[] userids){
		int val = userDao.batchRemove(userids);
		if (val > 0){
			for (Long userid : userids) {
				userDao.removeRole(userid);
			}
		}
		return val;
	}

    @Override
    public int savePwd(UserDO userDO) {
		return userDao.update(userDO);
    }
}
