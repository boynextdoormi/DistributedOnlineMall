package com.taoshop.common.pojo;

import java.util.List;

/***
 * 后台管理系统响应结构
 */
public class EasyUIResult {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
