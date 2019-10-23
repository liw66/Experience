package com.li.experience.modules.sys.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-23 16:25
 **/
public class TableDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableName;

    private String tableComment;

    private String engine;

    private String createTime;

    private List<ColumnDO> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ColumnDO> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDO> columns) {
        this.columns = columns;
    }
}
