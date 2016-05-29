package com.louisgeek.louiszhihuribao260_744.view.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.louisgeek.louiszhihuribao260_744.tool.ThemeTool;
import com.zhy.changeskin.SkinManager;

/**
 * Created by louisgeek on 2016/5/24.
 */
public abstract class LouisBaseAppCompatActivity extends AppCompatActivity {


    public String TAG=this.getClass().getSimpleName();
    private  int layoutID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 竖屏锁定
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        // requestWindowFeature(Window.FEATURE_NO_TITLE);//报错
         supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//还要加上 <item name="android:windowActionBar">false</item>

        //
        initContentViewLayoutID();
        setContentView(layoutID);

        initData();
        initView();
        initEventAndConfigAfterViewInited();

        SkinManager.getInstance().register(this);
        //初始化主题
        ThemeTool.setupTheme();

        //
    }
    protected abstract void initContentViewLayoutID();
    protected abstract void initData();
    protected abstract void initView();
    protected abstract void initEventAndConfigAfterViewInited();

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "---------onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "---------onResume ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "---------onStop ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "---------onPause ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "---------onRestart ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,  "---------onDestroy ");
        SkinManager.getInstance().unregister(this);
    }
}
