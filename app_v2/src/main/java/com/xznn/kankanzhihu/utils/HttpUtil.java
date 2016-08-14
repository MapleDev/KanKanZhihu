package com.xznn.kankanzhihu.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author MapleDev
 * @time 16/08/05  22:14
 * @desc ${TODD}
 */
public class HttpUtil {

    public static void doGet(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(callback);
    }
}
