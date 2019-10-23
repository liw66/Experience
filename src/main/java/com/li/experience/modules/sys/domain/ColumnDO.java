package com.li.experience.modules.sys.domain;

import java.io.Serializable;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-23 16:40
 **/
public class ColumnDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String columnName;

    private String columnComment;

    private String columnKey;

    private String dataType;

    private String extra;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

}
