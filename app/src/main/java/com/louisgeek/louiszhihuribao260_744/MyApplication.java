package com.louisgeek.louiszhihuribao260_744;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.zhy.changeskin.SkinManager;

/**
 * Created by louisgeek on 2016/5/24.
 */
public class MyApplication extends Application{
    SharedPreferences sharedPreferences;
    final  String nowThemeKey="NowTheme";
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);

        /*//将数据保存至SharedPreferences:
        sharedPreferences=getSharedPreferences("NowTheme", Context.MODE_PRIVATE);



        //从SharedPreferences获取数据:
        sharedPreferences=getSharedPreferences("NowTheme", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString(nowThemeKey, "black");*/
    }

    void  saveTheme(String value){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(nowThemeKey, value);
        editor.commit();
    }





}
