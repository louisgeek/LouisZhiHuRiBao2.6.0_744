package com.louisgeek.louiszhihuribao260_744.model.biz.impl;

import com.alibaba.fastjson.JSON;
import com.louisgeek.louiszhihuribao260_744.model.bean.StartImageBean;
import com.louisgeek.louiszhihuribao260_744.model.biz.IBizSplash;
import com.louisgeek.louiszhihuribao260_744.model.biz.OnGainDataListener;

/**
 * Created by louisgeek on 2016/5/29.
 */
public class BizSplashImpl implements IBizSplash{
    @Override
    public void gainSplashInfoBiz(String response,OnGainDataListener onBizSplashListener) {

        String jsonStr=response.toString();

        StartImageBean startImageBean = JSON.parseObject(jsonStr, StartImageBean.class);

        if (startImageBean!=null){
            onBizSplashListener.onSuccess(startImageBean);
        }else{
            onBizSplashListener.onFailed("等于空");
        }
    }
}
