package com.li.experience.modules.sys.service;

import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.domain.RoleDO;
import com.li.experience.modules.sys.domain.RoleVO;

import java.util.List;
import java.util.Map;

/**
 * 角色信息表
 *
 * @author Liwei
 * @email test@163.com
 * @date 2019-05-09 16:09:52
 */
public interface RoleService {

    RoleVO get(Long roleid);

    List<RoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RoleVO roleVO);

    int update(RoleVO roleVO);

    int remove(Long roleid);

    int batchRemove(Long[] roleids);

    Tree getRoleMenu(Long roleid);
}
