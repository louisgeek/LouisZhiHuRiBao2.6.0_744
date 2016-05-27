package com.louisgeek.louiszhihuribao260_744.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.bean.StartImageBean;
import com.louisgeek.louiszhihuribao260_744.custom.LouisAppCompatActivity;
import com.louisgeek.louiszhihuribao260_744.util.ScreenUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class SplashActivity extends LouisAppCompatActivity {

    private static final String TAG = "SplashActivity";
    ImageView idivsplash;
    TextView id_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        idivsplash = (ImageView) findViewById(R.id.id_iv_splash);
        id_tv= (TextView) findViewById(R.id.id_tv);

        RelativeLayout idrltitle = (RelativeLayout) findViewById(R.id.id_rl_title);

        TextView id_jump = (TextView) findViewById(R.id.id_jump);
        id_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain();
            }
        });
        Animation splash_title_anim = AnimationUtils.loadAnimation(this, R.anim.splash_title);
        idrltitle.startAnimation(splash_title_anim);

        Animation splash_jump_anim = AnimationUtils.loadAnimation(this, R.anim.splash_jump);
        splash_jump_anim.setStartOffset(2000);
        id_jump.startAnimation(splash_jump_anim);

        Log.d(TAG, "onCreate: getScreenWidth"+ScreenUtil.getScreenWidth(this));
        Log.d(TAG, "onCreate: getScreenHeight"+ScreenUtil.getScreenHeight(this));
        String start_image_url = "http://news-at.zhihu.com/api/4/start-image/720*1184";
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

        OkHttpUtils
                .get()
                .url(start_image_url)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(SplashActivity.this, "onError Exception:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response)
                    {
                       String jsonStr=response.toString();

                        StartImageBean startImageBean = JSON.parseObject(jsonStr, StartImageBean.class);

                        String imgUrl=startImageBean.getImg();
                        //感觉图片和官方不太一样  先用别的
                        imgUrl="http://image18-c.poco.cn/mypoco/myphoto/20160527/16/17499807820160527163311011.jpg?1080x1600_120";

                        Glide.with(getApplicationContext()).load(imgUrl).into(idivsplash);

                        String text=startImageBean.getText();
                        if(!text.equals("")){
                           id_tv.setText(text);
                        }


                    }
                });

            //x秒后，执行run方法启动主界面Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        }, 3*1000);
    }

    private void goToMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        //启动主Activity后销毁
        finish();
    }
}
