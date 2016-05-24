package com.louisgeek.louiszhihuribao260_744;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.zhy.changeskin.SkinManager;

/**
 * Created by louisgeek on 2016/5/24.
 */
public class LouisAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);//报错
         supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//还要加上 <item name="android:windowActionBar">false</item>

         SkinManager.getInstance().register(this);
         //初始化主题
         ThemeUtil.setupTheme();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }
}
