package com.li.experience.modules.sys.service.impl;

import com.li.experience.modules.sys.dao.DictDao;
import com.li.experience.modules.sys.domain.DictDO;
import com.li.experience.modules.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 字典信息表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-08-16 11:15:38
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public DictDO get(Long dictid) {
        DictDO dictDO = dictDao.get(dictid);
        return dictDO;
    }

    @Override
    public List<DictDO> list(Map<String, Object> map) {
        return dictDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return dictDao.count(map);
    }

    @Override
    public int save(DictDO dictDO) {
        int val = dictDao.save(dictDO);
        return val;
    }

    @Override
    public int update(DictDO dictDO) {
        int val = dictDao.update(dictDO);
        return val;
    }

    @Override
    public int remove(Long dictid) {
        int val = dictDao.remove(dictid);
        return val;
    }

    @Override
    public int batchRemove(Long[] dictids) {
        int val = dictDao.batchRemove(dictids);
        return val;
    }

}
