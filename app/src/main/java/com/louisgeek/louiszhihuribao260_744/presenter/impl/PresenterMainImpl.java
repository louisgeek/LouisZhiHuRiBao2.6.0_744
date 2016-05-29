package com.louisgeek.louiszhihuribao260_744.presenter.impl;

import com.louisgeek.louiszhihuribao260_744.model.bean.NewsDateBean;
import com.louisgeek.louiszhihuribao260_744.model.biz.IBizMain;
import com.louisgeek.louiszhihuribao260_744.model.biz.OnGainDataListener;
import com.louisgeek.louiszhihuribao260_744.model.biz.impl.BizMainImpl;
import com.louisgeek.louiszhihuribao260_744.presenter.IPresenterMain;
import com.louisgeek.louiszhihuribao260_744.view.IViewMain;

import java.util.List;

/**
 * Created by louisgeek on 2016/5/29.
 */
public class PresenterMainImpl implements IPresenterMain{

    private IViewMain mIViewMain;
    private IBizMain mIBizMain;

    public PresenterMainImpl(IViewMain mIViewMain) {
        mIBizMain=new BizMainImpl();
        this.mIViewMain = mIViewMain;
    }

    @Override
    public void getLeftListData() {

        mIBizMain.gainLeftListInfoBiz("XXX", new OnGainDataListener() {
            @Override
            public void onSuccess(Object object) {


                //
            }

            @Override
            public void onFailed(String errorStr) {

                mIViewMain.showMyError(errorStr);
            }
        });
    }

    @Override
    public void doGetNewsListData(List<NewsDateBean> newsDateList) {
        mIBizMain.gainNewsListInfoBiz(newsDateList, new OnGainDataListener() {
            @Override
            public void onSuccess(Object object) {


            }

            @Override
            public void onFailed(String errorStr) {
                mIViewMain.showMyError(errorStr);
            }
        });
    }

    @Override
    public void getPresonalData() {

        mIBizMain.gainLeftPersonalInfoBiz("222", new OnGainDataListener() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFailed(String errorStr) {
                mIViewMain.showMyError(errorStr);
            }
        });

    }

    @Override
    public void doGoToDetail(int groupPosition,int childPosition) {
        mIViewMain.goToDetail(groupPosition,childPosition);
    }
}
