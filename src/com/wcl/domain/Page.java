package com.wcl.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Page {
    private Integer currentPage;

    private Integer totalPage;

    private Integer totalCount;

    private List<Equipment> equipmentList = new ArrayList<>();

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", equipmentList=" + equipmentList +
                '}';
    }
}
