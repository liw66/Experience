package com.li.experience.modules.sys.dao;

import com.li.experience.modules.sys.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @author test
 * @email test@163.com
 * @date 2019-06-25 17:51:00
 */
@Mapper
public interface MenuDao {

    MenuDO get(Long menuid);

    List<MenuDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MenuDO menu);

    int update(MenuDO menu);

    int remove(Long menuid);

    int batchRemove(Long[] menuids);

    List<MenuDO> getMenu(Map<String, Object> map);
}
