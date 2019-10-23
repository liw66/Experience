package com.li.experience.modules.sys.service;

import com.li.experience.modules.sys.domain.LogDO;

import java.util.List;
import java.util.Map;

/**
 * 系统日志表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-08-08 15:40:00
 */
public interface LogService {

    LogDO get(Long logid);

    List<LogDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(LogDO logDO);

    int update(LogDO logDO);

    int remove(Long logid);

    int batchRemove(Long[] logids);

}
