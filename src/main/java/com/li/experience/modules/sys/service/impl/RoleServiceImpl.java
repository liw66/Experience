package com.li.experience.modules.sys.service.impl;

import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.dao.MenuDao;
import com.li.experience.modules.sys.dao.RoleDao;
import com.li.experience.modules.sys.domain.MenuDO;
import com.li.experience.modules.sys.domain.RoleDO;
import com.li.experience.modules.sys.domain.RoleVO;
import com.li.experience.modules.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-01 15:11
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public RoleVO get(Long roleid) {
        RoleVO roleVO = new RoleVO();
        List<Long> menuids = roleDao.getMenu(roleid);
        roleVO.setMenuids(menuids);
        RoleDO roleDO = roleDao.get(roleid);
        BeanUtils.copyProperties(roleDO,roleVO);
        return roleVO;
    }

    @Override
    public List<RoleDO> list(Map<String, Object> map) {
        return roleDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return roleDao.count(map);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int save(RoleVO roleVO) {
        RoleDO roleDO = new RoleDO();
        String[] ignoreProperties = new String[]{"menuids"};
        BeanUtils.copyProperties(roleVO,roleDO,ignoreProperties);
        int val = roleDao.save(roleDO);
        if (val > 0){
            List list = new ArrayList();
            for (int i = 0; i < roleVO.getMenuids().size(); i++) {
                Map map = new HashMap();
                map.put("roleid",roleDO.getRoleid());
                map.put("menuid",roleVO.getMenuids().get(i));
                list.add(map);
            }
            roleDao.saveMenu(list);
        }
        return val;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int update(RoleVO roleVO) {
        RoleDO roleDO = new RoleDO();
        String[] ignoreProperties = new String[]{"menuids"};
        BeanUtils.copyProperties(roleVO,roleDO,ignoreProperties);
        int val = roleDao.update(roleDO);
        if (val > 0){
            roleDao.removeMenu(roleVO.getRoleid());
            List list = new ArrayList();
            for (int i = 0; i < roleVO.getMenuids().size(); i++) {
                Map map = new HashMap();
                map.put("roleid",roleDO.getRoleid());
                map.put("menuid",roleVO.getMenuids().get(i));
                list.add(map);
            }
            roleDao.saveMenu(list);
        }
        return val;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int remove(Long roleid) {
        int val = roleDao.remove(roleid);
        if (val > 0){
            roleDao.removeMenu(roleid);
        }
        return val;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int batchRemove(Long[] roleids) {
        int val = roleDao.batchRemove(roleids);
        if (val > 0){
            for (int i = 0; i < roleids.length; i++) {
                roleDao.removeMenu(roleids[i]);
            }
        }
        return val;
    }

    @Override
    public Tree getRoleMenu(Long roleid) {
        List<Long> menuids = roleDao.getMenu(roleid);
        Tree tree = getParent(menuids);
        return tree;
    }

    public Tree getParent(List<Long> menuids) {
        List<Tree> trees = new ArrayList();

        List<MenuDO> list = menuDao.list(new HashMap<>());
        for (MenuDO d : list) {
            if (d.getParentid() == null) {
                Tree tree = new Tree();
                tree.setId(d.getMenuid().toString());
                tree.setText(d.getMenuname());
                tree.setIcon(d.getIcon());
                List<Tree> childTree = getChild(d.getMenuid(),menuids);
                if (childTree.size() > 0) {
                    tree.setChildren(childTree);
                }
                trees.add(tree);
            }
        }
        Tree root = new Tree();
        root.setId("0");
        root.setText("根节点");
        root.setChildren(trees);
        return root;
    }

    private List<Tree> getChild(Long parentid,List<Long> menuids) {
        List<Tree> trees = new ArrayList();
        Map map = new HashMap();
        map.put("parentid", parentid);
        List<MenuDO> childList = menuDao.list(map);
        for (MenuDO d : childList) {
            Tree tree = new Tree();
            tree.setId(d.getMenuid().toString());
            tree.setText(d.getMenuname());
            tree.setIcon(d.getIcon());
            List<Tree> childTree = getChild(d.getMenuid(),menuids);
            if (childTree.size() > 0) {
                tree.setChildren(childTree);
            }else{
                Map state = new HashMap();
                for (Long menuid : menuids) {
                    if (d.getMenuid() == menuid){
                        state.put("selected", true);
                    }
                }
                tree.setState(state);
            }
            trees.add(tree);
        }
        return trees;
    }
}
