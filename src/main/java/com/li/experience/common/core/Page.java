package com.li.experience.common.core;

import java.io.Serializable;
import java.util.List;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-05-10 14:37
 **/
public class Page implements Serializable {

    private int total;
    private List rows;

    public Page(List rows, int total){
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
