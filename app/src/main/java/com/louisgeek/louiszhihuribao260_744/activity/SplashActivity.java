package com.louisgeek.louiszhihuribao260_744.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.bean.StartImageBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class SplashActivity extends AppCompatActivity {

    ImageView idivsplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         idivsplash = (ImageView) findViewById(R.id.id_iv_splash);


        String start_image_url_720_1184 = "http://news-at.zhihu.com/api/4/start-image/720*1184";
        String start_image_url_1080_1776 = "http://news-at.zhihu.com/api/4/start-image/1080*1776";
        OkHttpUtils
                .get()
                .url(start_image_url_720_1184)
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
                        Glide.with(getApplicationContext()).load(startImageBean.getImg()).into(idivsplash);
                    }
                });

            //x秒后，执行run方法启动主界面Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                //启动主Activity后销毁
                finish();
            }
        }, 3*1000);
    }
}
