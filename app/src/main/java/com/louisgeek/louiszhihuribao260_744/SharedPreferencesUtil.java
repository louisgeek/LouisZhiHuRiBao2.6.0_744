package com.louisgeek.louiszhihuribao260_744;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by louisgeek on 2016/5/24.
 */
public class SharedPreferencesUtil {
    private final static String SHARED_PREFERENCES_FILENAME="SP_NowTheme";
    public  final static String NOW_THEME_KEY="NowTheme";

    /**将数据保存至SharedPreferences*/
    public static void  saveThemeValue(Context context,String nowthemeValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(NOW_THEME_KEY, nowthemeValue);
        editor.commit();
    }
    /**从SharedPreferences获取数据*/
    public static String  getThemeValue(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE);
        String themeValue=sharedPreferences.getString(NOW_THEME_KEY, Constants.THEME_DEFAULT_THEMEFLAG);
        return themeValue;
    }
}
