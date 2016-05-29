package com.louisgeek.louiszhihuribao260_744.presenter;

import com.louisgeek.louiszhihuribao260_744.model.bean.NewsDateBean;

import java.util.List;

/**
 * Created by louisgeek on 2016/5/29.
 */
public interface IPresenterMain {
    void getLeftListData();
    void doGetNewsListData(List<NewsDateBean> newsDateList);
    void getPresonalData();

    void doGoToDetail(int groupPosition,int childPosition);
}
