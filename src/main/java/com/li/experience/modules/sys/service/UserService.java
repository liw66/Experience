package com.li.experience.modules.sys.service;

import com.li.experience.modules.sys.domain.UserAO;
import com.li.experience.modules.sys.domain.UserDO;
import com.li.experience.modules.sys.domain.UserVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户信息表
 *
 * @author Liwei
 * @email test@163.com
 * @date 2019-05-09 16:09:52
 */
public interface UserService {

    Set findRoles(String username);

    Set findPermissions(String username);

    UserVO get(Long userid);

    UserDO getByName(String username);

    List<UserDO> list(Map<String, Object> map);

    List<UserAO> export(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserVO user);

    int update(UserVO user);

    int remove(Long userid);

    int batchRemove(Long[] userids);

    int savePwd(UserDO userDO);
}
