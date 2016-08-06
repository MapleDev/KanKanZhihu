package com.xznn.kankanzhihu;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author MapleDev
 * @time 16/07/29  15:38
 * @desc ${TODD}
 */
public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .addInterceptor(new LoggerInterceptor("TAG"))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                //其他配置
//                .build();
//
        OkHttpUtils.initClient(okHttpClient);


        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).build();
        OkHttpClient

    }
}