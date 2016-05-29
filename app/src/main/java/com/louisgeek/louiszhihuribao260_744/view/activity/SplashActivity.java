package com.louisgeek.louiszhihuribao260_744.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.app.LouisApplication;
import com.louisgeek.louiszhihuribao260_744.presenter.IPresenterSplash;
import com.louisgeek.louiszhihuribao260_744.presenter.impl.PresenterSplashImpl;
import com.louisgeek.louiszhihuribao260_744.util.ScreenUtil;
import com.louisgeek.louiszhihuribao260_744.view.IViewSplash;
import com.louisgeek.louiszhihuribao260_744.view.common.LouisBaseAppCompatActivity;

public class SplashActivity extends LouisBaseAppCompatActivity implements IViewSplash{

    private static final String TAG = "SplashActivity";
    ImageView idivsplash;
    TextView id_tv;

    RelativeLayout idrltitle;
    TextView id_jump;

    String start_image_url;


    IPresenterSplash mIPresenterSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_splash);

        mIPresenterSplash=new PresenterSplashImpl(this,start_image_url);
        mIPresenterSplash.doGetJsonInfoData();
        mIPresenterSplash.doAutoGoToMainAtyDelay(3*1000);

    }

    @Override
    protected void initContentViewLayoutID() {
        super.setLayoutID(R.layout.activity_splash);
    }

    @Override
    protected void initData() {
        Log.d(TAG, "onCreate: getScreenWidth"+ScreenUtil.getScreenWidth(this));
        Log.d(TAG, "onCreate: getScreenHeight"+ScreenUtil.getScreenHeight(this));
        start_image_url = "http://news-at.zhihu.com/api/4/start-image/720*1184";
        float width=ScreenUtil.getScreenWidth(this);
        if (width<320) {
            start_image_url = "http://news-at.zhihu.com/api/4/start-image/320*432";//480   -48
        }else if(width<480) {
            start_image_url = "http://news-at.zhihu.com/api/4/start-image/480*728";//768    -40
        }else if(width<720) {
            start_image_url = "http://news-at.zhihu.com/api/4/start-image/720*1184";//1280    -96
        }else if(width<1080) {
            start_image_url = "http://news-at.zhihu.com/api/4/start-image/1080*1776";//1920   -144
        }
    }

    @Override
    protected void initView() {
        idivsplash = (ImageView) findViewById(R.id.id_iv_splash);
        id_tv= (TextView) findViewById(R.id.id_tv);

         idrltitle = (RelativeLayout) findViewById(R.id.id_rl_title);

         id_jump = (TextView) findViewById(R.id.id_jump);


        initEventAndConfigAfterViewInited();
    }

    @Override
    protected void initEventAndConfigAfterViewInited() {

        id_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIPresenterSplash.doGoToMainAty();
            }
        });




        configAnim();
    }

    private void configAnim() {
        //
        Animation splash_title_anim = AnimationUtils.loadAnimation(this, R.anim.splash_title);
        idrltitle.startAnimation(splash_title_anim);


        Animation splash_jump_anim = AnimationUtils.loadAnimation(this, R.anim.splash_jump);
        splash_jump_anim.setStartOffset(2000);
        id_jump.startAnimation(splash_jump_anim);
    }


    @Override
    public void showLoadingView() {
//=====
    }

    @Override
    public void hideLoadingView() {
//=====
    }

    @Override
    public void goToMainAty() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        //启动主Activity后销毁
        finish();
    }

    @Override
    public void setupTxt(String txt) {
        id_tv.setText(txt);
    }

    @Override
    public void setupImg(String imgUrl) {
        Glide.with(LouisApplication.getAppContext()).load(imgUrl).into(idivsplash);
    }

    @Override
    public void showFailedError(Exception e) {
        Toast.makeText(LouisApplication.getAppContext(), "onError Exception:"+e.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showMyError(String errorStr) {
        Toast.makeText(LouisApplication.getAppContext(), "showMyError:"+errorStr, Toast.LENGTH_SHORT).show();

    }


}
