package com.tlyy.spider.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.omg.CORBA.TIMEOUT;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
public class HttpUtil {
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .connectionPool(new ConnectionPool())
            .build();

    public static String doGet(String url) {
        Request request = new Request.Builder().url(url).build();
        Call call = CLIENT.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
