package com.li.experience.modules.sys.service;

import com.li.experience.modules.sys.domain.TableDO;

import java.util.List;
import java.util.Map;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-19 14:16
 **/
public interface CodeService {

    List<TableDO> list();

    byte[] code(String[] tableNames);
}
