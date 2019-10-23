package com.li.experience.modules.sys.service.impl;

import com.li.experience.common.utils.CodeUtils;
import com.li.experience.modules.sys.dao.CodeDao;
import com.li.experience.modules.sys.domain.ColumnDO;
import com.li.experience.modules.sys.domain.TableDO;
import com.li.experience.modules.sys.service.CodeService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-19 14:39
 **/
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;

    @Override
    public List<TableDO> list() {
        return codeDao.list();
    }

    @Override
    public byte[] code(String[] tableNames) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(stream);
        List<TableDO> list = codeDao.list();

        for (String tableName : tableNames){
            TableDO table = list.stream().filter(t->t.getTableName().equals(tableName)).collect(Collectors.toList()).get(0);
            List<ColumnDO> columns = codeDao.getColunms(tableName);
            for (ColumnDO column : columns){
                String javaType = CodeUtils.getJavaType(column.getDataType());
                column.setDataType(javaType);
                String funName = WordUtils.capitalize(column.getColumnName());
                column.setExtra(funName);
            }
            table.setColumns(columns);
            try {
                CodeUtils.buildFtlCode(table,zip);//使用FreeMarker模板
                //CodeUtils.buildVmCode(table,zip);//使用Velocity模板
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        IOUtils.closeQuietly(zip);
        return stream.toByteArray();
    }
}
