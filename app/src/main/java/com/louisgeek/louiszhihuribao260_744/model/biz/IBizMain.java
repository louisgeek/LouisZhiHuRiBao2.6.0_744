package com.louisgeek.louiszhihuribao260_744.model.biz;

import com.louisgeek.louiszhihuribao260_744.model.bean.NewsDateBean;

import java.util.List;

/**
 * Created by louisgeek on 2016/5/29.
 */
public interface IBizMain {
   void gainLeftPersonalInfoBiz(String response, OnGainDataListener onGainDataListener);
    void gainLeftListInfoBiz(String response, OnGainDataListener onGainDataListener);
    void gainNewsListInfoBiz(List<NewsDateBean> newsDateList, OnGainDataListener onGainDataListener);
}
