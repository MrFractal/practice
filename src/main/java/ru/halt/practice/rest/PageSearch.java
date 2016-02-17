package ru.halt.practice.rest;

/**
 * Created by Petr Rudenko on 31.01.2016.
 */
public class PageSearch extends PageFilter{
    private Long id;
    private String search;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
