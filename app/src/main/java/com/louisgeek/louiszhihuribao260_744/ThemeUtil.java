package com.louisgeek.louiszhihuribao260_744;

import android.content.Context;

import com.zhy.changeskin.SkinManager;

/**
 * Created by louisgeek on 2016/5/24.
 */
public class ThemeUtil {

    public static void setupTheme(){
         String nowthemeValue=InfoHolderSingleton.getInstance().getMapObj(Constants.INFOHOLDER_NOW_THEME_KEY).toString();
        if (nowthemeValue.equals(Constants.THEME_DEFAULT_THEMEFLAG)){
            SkinManager.getInstance().removeAnySkin();
        }else if(nowthemeValue.equals(Constants.THEME_BLACK_THEMEFLAG))
        {
            SkinManager.getInstance().changeSkin(Constants.THEME_BLACK_THEMEFLAG);
        }
    }

    /**
     * SkinManager.getInstance().changeSkin(suffix);
     * 多套皮肤以后缀就行区别，比如：main_bg，皮肤资源可以为：main_bg_red,main_bg_green等。

     换肤时，直接传入后缀，例如上面描述的red,green。
     */
    public static  void changeTheme(Context context) {
        //读取
        String nowThemeValue=InfoHolderSingleton.getInstance().getMapObj(Constants.INFOHOLDER_NOW_THEME_KEY).toString();
        if (nowThemeValue.equals(Constants.THEME_DEFAULT_THEMEFLAG)){
            SkinManager.getInstance().changeSkin(Constants.THEME_BLACK_THEMEFLAG);
            nowThemeValue=Constants.THEME_BLACK_THEMEFLAG;
        }else if(nowThemeValue.equals(Constants.THEME_BLACK_THEMEFLAG)){
            SkinManager.getInstance().removeAnySkin();
            nowThemeValue=Constants.THEME_DEFAULT_THEMEFLAG;
        }
        //存入新的
        InfoHolderSingleton.getInstance().putMapObj(Constants.INFOHOLDER_NOW_THEME_KEY,nowThemeValue);
        //保存文件
        SharedPreferencesUtil.saveThemeValue(context,nowThemeValue);
    }
}
