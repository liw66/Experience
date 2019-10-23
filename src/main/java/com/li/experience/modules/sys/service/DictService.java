package com.li.experience.modules.sys.service;

import com.li.experience.modules.sys.domain.DictDO;

import java.util.List;
import java.util.Map;

/**
 * 字典信息表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-08-16 11:15:38
 */
public interface DictService {

    DictDO get(Long dictid);

    List<DictDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DictDO dictDO);

    int update(DictDO dictDO);

    int remove(Long dictid);

    int batchRemove(Long[] dictids);

}
