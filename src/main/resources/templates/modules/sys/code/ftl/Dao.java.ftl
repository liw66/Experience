package com.li.experience.modules.app.dao;

import com.li.experience.modules.app.domain.${className}DO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ${tableComment}
 *
 * @author Liwei
 * @email liw66@163.com
 * @date ${createTime}
 */
@Mapper
public interface ${className}Dao {

    ${className}DO get(Long ${pk});

    List<${className}DO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(${className}DO ${lowerName}DO);

    int update(${className}DO ${lowerName}DO);

    int remove(Long ${pk});

    int batchRemove(Long[] ${pk}s);

}
