package com.saida_aliyeva.weather.model;

import java.util.List;

public class SectionDataModel {

    private String date;
    private List<List2> list2List;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<List2> getList2List() {
        return list2List;
    }

    public void setList2List(List<List2> list2List) {
        this.list2List = list2List;
    }


    @Override
    public String toString() {
        return "SectionDataModel{" +
                "date='" + date + '\'' +
                ", list2List=" + list2List +
                '}';
    }
}

