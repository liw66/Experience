package com.li.experience.modules.sys.service;

import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.domain.MenuDO;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单表
 *
 * @author Liwei
 * @email test@163.com
 * @date 2019-06-25 16:09:52
 */
public interface MenuService {

    MenuDO get(Long menuid);

    List<MenuDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MenuDO menu);

    int update(MenuDO menu);

    int remove(Long menuid);

    List<Tree> getMenu();



}
