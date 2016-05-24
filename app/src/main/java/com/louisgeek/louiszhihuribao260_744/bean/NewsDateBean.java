package com.louisgeek.louiszhihuribao260_744.bean;

import java.util.List;

/**
 * Created by louisgeek on 2016/5/23.
 */
public class NewsDateBean {
    private  String dateStr;
    private List<NewsBean> newsBeanList;
    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
    public List<NewsBean> getNewsBeanList() {
        return newsBeanList;
    }

    public void setNewsBeanList(List<NewsBean> newsBeanList) {
        this.newsBeanList = newsBeanList;
    }
}
