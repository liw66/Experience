package com.li.experience.modules.sys.service.impl;

import com.li.experience.modules.sys.dao.LogDao;
import com.li.experience.modules.sys.domain.LogDO;
import com.li.experience.modules.sys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统日志表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-08-08 15:40:00
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public LogDO get(Long logid) {
        LogDO logDO = logDao.get(logid);
        return logDO;
    }

    @Override
    public List<LogDO> list(Map<String, Object> map) {
        return logDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return logDao.count(map);
    }

    @Override
    public int save(LogDO logDO) {
        int val = logDao.save(logDO);
        return val;
    }

    @Override
    public int update(LogDO logDO) {
        int val = logDao.update(logDO);
        return val;
    }

    @Override
    public int remove(Long logid) {
        int val = logDao.remove(logid);
        return val;
    }

    @Override
    public int batchRemove(Long[] logids) {
        int val = logDao.batchRemove(logids);
        return val;
    }

}
