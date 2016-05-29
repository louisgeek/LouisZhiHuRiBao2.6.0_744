package com.louisgeek.louiszhihuribao260_744.presenter.impl;

import android.os.Handler;

import com.louisgeek.louiszhihuribao260_744.model.bean.StartImageBean;
import com.louisgeek.louiszhihuribao260_744.model.biz.IBizSplash;
import com.louisgeek.louiszhihuribao260_744.model.biz.OnGainDataListener;
import com.louisgeek.louiszhihuribao260_744.model.biz.impl.BizSplashImpl;
import com.louisgeek.louiszhihuribao260_744.presenter.IPresenterSplash;
import com.louisgeek.louiszhihuribao260_744.view.IViewSplash;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by louisgeek on 2016/5/29.
 */
public class PresenterSplashImpl implements IPresenterSplash{

    private IBizSplash mIBizSplash;
    private IViewSplash mIViewSplash;
    String start_image_url;
    public PresenterSplashImpl(IViewSplash mIViewSplash,String start_image_url) {
        mIBizSplash = new BizSplashImpl();
        this.mIViewSplash = mIViewSplash;
        this.start_image_url=start_image_url;
    }

    @Override
    public void doGetJsonInfoData() {

        mIViewSplash.showLoadingView();


        OkHttpUtils
                .get()
                .url(start_image_url)
                .addParams("username", "abc")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e) {

                        mIViewSplash.hideLoadingView();
                        mIViewSplash.showFailedError(e);
                    }

                    @Override
                    public void onResponse(String response)
                    {
                        mIViewSplash.hideLoadingView();

                        mIBizSplash.gainSplashInfoBiz(response,new OnGainDataListener() {
                            @Override
                            public void onSuccess(Object object) {

                                StartImageBean startImageBean= (StartImageBean) object;
                                String imgUrl=startImageBean.getImg();

                                //测试用的。。。。
                                //感觉图片和官方不太一样  先用别的
                                imgUrl="http://image18-c.poco.cn/mypoco/myphoto/20160527/16/17499807820160527163311011.jpg?1080x1600_120";
                                //测试用的。。。。

                                mIViewSplash.setupImg(imgUrl);


                                String text=startImageBean.getText();
                                if(!text.equals("")){
                                    mIViewSplash.setupTxt(text);
                                }
                            }

                            @Override
                            public void onFailed(String errorStr) {

                                mIViewSplash.showMyError(errorStr);
                                //
                            }
                        });


                    }
                });



    }

    @Override
    public void doAutoGoToMainAtyDelay(long delayMillis) {
        //x秒后，执行run方法启动主界面Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIViewSplash.goToMainAty();
            }
        }, delayMillis);

    }

    @Override
    public void doGoToMainAty() {
        mIViewSplash.goToMainAty();
    }

}
