package com.li.experience.modules.sys.service;

import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门信息表
 *
 * @author Liwei
 * @email test@163.com
 * @date 2019-05-09 16:09:52
 */
public interface DeptService {

    DeptDO get(Long deptid);

    List<DeptDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DeptDO dept);

    int update(DeptDO dept);

    int remove(Long deptid);

    List<Tree> getTree();
}
