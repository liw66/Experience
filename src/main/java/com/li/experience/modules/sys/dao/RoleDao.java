package com.li.experience.modules.sys.dao;


import com.li.experience.modules.sys.domain.RoleDO;
import com.li.experience.modules.sys.domain.RoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户角色
 *
 * @author test
 * @email test@163.com
 * @date 2019-06-25 17:51:00
 */
@Mapper
public interface RoleDao {

    RoleDO get(Long roleid);

    List<RoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RoleDO roleDO);

    int update(RoleDO roleDO);

    int remove(Long roleid);

    int batchRemove(Long[] roleids);

    int removeMenu(Long roleid);

    int saveMenu(List list);

    List<Long> getMenu(Long roleid);
}
