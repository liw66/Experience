package com.li.experience.modules.sys.dao;

import com.li.experience.modules.sys.domain.DictDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 字典信息表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-08-16 11:15:38
 */
@Mapper
public interface DictDao {

    DictDO get(Long dictid);

    List<DictDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DictDO dictDO);

    int update(DictDO dictDO);

    int remove(Long dictid);

    int batchRemove(Long[] dictids);

}
