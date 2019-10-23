package com.li.experience.modules.sys.service.impl;

import com.li.experience.common.core.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.li.experience.modules.sys.dao.DeptDao;
import com.li.experience.modules.sys.domain.DeptDO;
import com.li.experience.modules.sys.service.DeptService;


@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public DeptDO get(Long deptid) {
        return deptDao.get(deptid);
    }

    @Override
    public List<DeptDO> list(Map<String, Object> map) {
        return deptDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return deptDao.count(map);
    }

    @Override
    public int save(DeptDO dept) {
        return deptDao.save(dept);
    }

    @Override
    public int update(DeptDO dept) {
        return deptDao.update(dept);
    }

    @Override
    public int remove(Long deptid) {
        return deptDao.remove(deptid);
    }

    @Override
    public List<Tree> getTree() {
        List<Tree> trees = new ArrayList();
        List<DeptDO> list = deptDao.list(new HashMap<>());
        for (DeptDO d : list) {
            if (d.getParentid() == null) {
                Tree tree = new Tree();
                tree.setId(d.getDeptid().toString());
                tree.setText(d.getDeptname());
                Map state = new HashMap();
                state.put("opened", true);
                tree.setState(state);
                List<Tree> childTree = getChild(d.getDeptid());
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
        map.put("parentid", parentid);
        List<DeptDO> childList = deptDao.list(map);
        for (DeptDO d : childList) {
            Tree tree = new Tree();
            tree.setId(d.getDeptid().toString());
            tree.setText(d.getDeptname());
            List<Tree> childTree = getChild(d.getDeptid());
            if (childTree.size() > 0) {
                tree.setChildren(childTree);
            }
            trees.add(tree);
        }
        return trees;
    }
}
