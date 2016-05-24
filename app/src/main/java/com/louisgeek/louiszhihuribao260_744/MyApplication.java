package com.louisgeek.louiszhihuribao260_744;

import android.app.Application;

import com.zhy.changeskin.SkinManager;

/**
 * Created by louisgeek on 2016/5/24.
 */
public class MyApplication extends Application{

    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);

        initThemeSetting();

    }

    private void initThemeSetting() {
        String nowthemeValue=SharedPreferencesUtil.getThemeValue(getApplicationContext());
        //存入InfoHolderSingleton
        InfoHolderSingleton.getInstance().putMapObj(Constants.INFOHOLDER_NOW_THEME_KEY,nowthemeValue);
    }




}
