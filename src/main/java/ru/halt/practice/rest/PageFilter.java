package ru.halt.practice.rest;

/**
 * Created by Petr Rudenko on 31.01.2016.
 */
public class PageFilter {
    private int page;
    private int size;
    private String sord;
    private String sidx;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
