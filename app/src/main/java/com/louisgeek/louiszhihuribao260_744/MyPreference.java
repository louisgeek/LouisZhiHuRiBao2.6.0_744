package com.louisgeek.louiszhihuribao260_744;

import android.content.Context;
import android.preference.Preference;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by louisgeek on 2016/5/24.
 */
public class MyPreference extends Preference {
    public MyPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPreference(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        //return super.onCreateView(parent);
        //生成View完后 自定义
        View view = super.onCreateView(parent);

        TextView titleView = (TextView) view.findViewById(android.R.id.title);
       /* Typeface tf = Typeface.createFromAsset(view.getContext().getAssets(), robotoRegular);
        titleView.setTypeface(tf);*/

        String nowThemeValue=InfoHolderSingleton.getInstance().getMapObj(Constants.INFOHOLDER_NOW_THEME_KEY).toString();
        if (nowThemeValue.equals(Constants.THEME_DEFAULT_THEMEFLAG)) {
            titleView.setTextColor(ContextCompat.getColor(getContext(), R.color.setting_title_text_color));
        }else if (nowThemeValue.equals(Constants.THEME_BLACK_THEMEFLAG)) {
            titleView.setTextColor(ContextCompat.getColor(getContext(), R.color.setting_title_text_color_black));
        }
        return view;
    }
}
