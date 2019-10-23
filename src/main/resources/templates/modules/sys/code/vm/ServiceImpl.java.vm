package com.li.experience.modules.app.service.impl;

import com.li.experience.modules.app.dao.${className}Dao;
import com.li.experience.modules.app.domain.${className}DO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ${tableComment}
 *
 * @author Liwei
 * @email liw66@163.com
 * @date ${createTime}
 */
@Service
public class ${className}ServiceImpl implements ${className}Service {

    @Autowired
    private ${className}Dao ${lowerName}Dao;

    @Override
    public ${className}DO get(Long ${pk}) {
        ${className}DO ${lowerName}DO = ${lowerName}Dao.get(${pk});
        return ${lowerName}DO;
    }

    @Override
    public List<${className}DO> list(Map<String, Object> map) {
        return ${lowerName}Dao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return ${lowerName}Dao.count(map);
    }

    @Override
    public int save(${className}DO ${lowerName}DO) {
        int val = ${lowerName}Dao.save(${lowerName}DO);
        return val;
    }

    @Override
    public int update(${className}DO ${lowerName}DO) {
        int val = ${lowerName}Dao.update(${lowerName}DO);
        return val;
    }

    @Override
    public int remove(Long ${pk}) {
        int val = ${lowerName}Dao.remove(${pk});
        return val;
    }

    @Override
    public int batchRemove(Long[] ${pk}s) {
        int val = ${lowerName}Dao.batchRemove(${pk}s);
        return val;
    }

}
