package com.li.experience.modules.sys.dao;

import com.li.experience.modules.sys.domain.ColumnDO;
import com.li.experience.modules.sys.domain.TableDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-19 14:40
 **/
@Mapper
public interface CodeDao {

    @Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables \n" +
            "where table_schema = (select database())\n" +
            "order by CREATE_TIME desc\n")
    List<TableDO> list();

    @Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns \n" +
            "where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position")
    List<ColumnDO> getColunms(String tableName);
}
