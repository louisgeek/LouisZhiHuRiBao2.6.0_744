package com.louisgeek.louiszhihuribao260_744.custom;

import android.app.Application;
import android.content.Context;

import com.louisgeek.louiszhihuribao260_744.info.Constants;
import com.louisgeek.louiszhihuribao260_744.tool.HolderSingletonTool;
import com.louisgeek.louiszhihuribao260_744.util.ThemeSharedPreferencesUtil;
import com.zhy.changeskin.SkinManager;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by louisgeek on 2016/5/24.
 */
public class LouisApplication extends Application{

    private static final String TAG = "LouisApplication";
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
        context=getApplicationContext();
        initThemeSetting();
        initOkHttpUtils();
    }

    private void initThemeSetting() {
        String nowthemeValue= ThemeSharedPreferencesUtil.getThemeValue(getApplicationContext());
        //存入InfoHolderSingleton
        HolderSingletonTool.getInstance().putMapObj(Constants.INFOHOLDER_NOW_THEME_KEY,nowthemeValue);
    }

    private void initOkHttpUtils(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }


    public static Context getAppContext(){
        return context;
    }
}
