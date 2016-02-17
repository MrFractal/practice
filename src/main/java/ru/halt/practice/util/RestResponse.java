package ru.halt.practice.util;

/**
 * Created by Petr Rudenko on 25.01.2016.
 */
public class RestResponse<T> {
    private T data;
    private long totalRows;
    private int page;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
