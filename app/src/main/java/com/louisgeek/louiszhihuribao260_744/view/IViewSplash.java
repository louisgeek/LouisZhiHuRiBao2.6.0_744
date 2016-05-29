package com.louisgeek.louiszhihuribao260_744.view;

/**
 * Created by louisgeek on 2016/5/29.
 */
public interface IViewSplash{

    void showLoadingView();

    void hideLoadingView();

    void showFailedError(Exception e);

    void showMyError(String error);

    void goToMainAty();

    void setupTxt(String txt);

    void setupImg(String imgUrl);
}
