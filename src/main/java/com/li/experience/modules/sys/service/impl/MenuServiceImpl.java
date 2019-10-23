package com.li.experience.modules.sys.service.impl;

import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.dao.MenuDao;
import com.li.experience.modules.sys.domain.MenuDO;
import com.li.experience.modules.sys.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-06-25 17:41
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public MenuDO get(Long menuid) {
        return menuDao.get(menuid);
    }

    @Override
    public List<MenuDO> list(Map<String, Object> map) {
        return menuDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return menuDao.count(map);
    }

    @Override
    public int save(MenuDO menu) {
        return menuDao.save(menu);
    }

    @Override
    public int update(MenuDO menu) {
        return menuDao.update(menu);
    }

    @Override
    public int remove(Long menuid) {
        return menuDao.remove(menuid);
    }

    @Override
    public List<Tree> getMenu() {
        List<Tree> trees = new ArrayList();
        Map map = new HashMap();
        map.put("username", SecurityUtils.getSubject().getPrincipal().toString());
        List<MenuDO> list = menuDao.getMenu(map);
        for (MenuDO d : list) {
            if (d.getParentid() == null) {
                Tree tree = new Tree();
                tree.setId(d.getMenuid().toString());
                tree.setText(d.getMenuname());
                tree.setIcon(d.getIcon());
                Map state = new HashMap();
                state.put("url", d.getUrl());
                tree.setState(state);
                List<Tree> childTree = getChild(d.getMenuid());
                if (childTree.size() > 0) {
                    tree.setChildren(childTree);
                }
                trees.add(tree);
            }
        }
        return trees;
    }

    private List<Tree> getChild(Long parentid) {
        List<Tree> trees = new ArrayList();
        Map map = new HashMap();
        map.put("username", SecurityUtils.getSubject().getPrincipal().toString());
        map.put("parentid", parentid);
        List<MenuDO> childList = menuDao.getMenu(map);
        for (MenuDO d : childList) {
            Tree tree = new Tree();
            tree.setId(d.getMenuid().toString());
            tree.setText(d.getMenuname());
            tree.setIcon(d.getIcon());
            Map state = new HashMap();
            state.put("url", d.getUrl());
            tree.setState(state);
            List<Tree> childTree = getChild(d.getMenuid());
            if (childTree.size() > 0) {
                tree.setChildren(childTree);
            }
            trees.add(tree);
        }
        return trees;
    }
}
