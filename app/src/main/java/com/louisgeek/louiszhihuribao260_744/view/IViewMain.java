package com.louisgeek.louiszhihuribao260_744.view;

/**
 * Created by louisgeek on 2016/5/29.
 */
public interface IViewMain {

    void showLoadingView();

    void hideLoadingView();

    void showFailedError(Exception e);

    void showMyError(String error);

    void goToDetail(int groupPosition,int childPosition);

    void reloadData();

    void loadMoreData();
}
