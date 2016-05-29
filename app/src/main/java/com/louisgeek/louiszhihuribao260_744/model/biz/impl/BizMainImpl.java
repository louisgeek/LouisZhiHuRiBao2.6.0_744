package com.louisgeek.louiszhihuribao260_744.model.biz.impl;

import com.louisgeek.louiszhihuribao260_744.model.bean.NewsDateBean;
import com.louisgeek.louiszhihuribao260_744.model.biz.IBizMain;
import com.louisgeek.louiszhihuribao260_744.model.biz.OnGainDataListener;

import java.util.List;

/**
 * Created by louisgeek on 2016/5/29.
 */
public class BizMainImpl implements IBizMain {



    @Override
    public void gainLeftPersonalInfoBiz(String response, OnGainDataListener onGainDataListener) {
        String jsonStr=response.toString();
        if (true){
            onGainDataListener.onSuccess(null);
        }else{
            onGainDataListener.onFailed("等于空");
        }
    }

    @Override
    public void gainLeftListInfoBiz(String response, OnGainDataListener onGainDataListener) {
        String jsonStr=response.toString();

        if (true){
            onGainDataListener.onSuccess(null);
        }else{
            onGainDataListener.onFailed("等于空");
        }
    }
    @Override
    public void gainNewsListInfoBiz(List<NewsDateBean> newsDateList, OnGainDataListener onGainDataListener) {

        if (newsDateList!=null&&newsDateList.size()>0){
            onGainDataListener.onSuccess(newsDateList);
        }else{
            onGainDataListener.onFailed("等于空");
        }
    }
}
