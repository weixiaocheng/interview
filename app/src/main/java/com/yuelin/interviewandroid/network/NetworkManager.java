package com.yuelin.interviewandroid.network;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.model.ApiResponse;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkManager {

    public static NetworkManager getInstance = new NetworkManager();
    public static final Gson GSON = new Gson();

    /**
     * get 请求这里是已经拼接好的最后一步的请求
     * @param url
     * @param callBack
     */
    public void dioGet(String url, ICallBack callBack) {
        // 这里将线程的问题处理一下
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();
                    // 获取返回的结果 判断是否正确
                    final ResponseBody body = response.body();
                    // 这样就判断请求失败了
                    if (body.string().indexOf("isError\":true") > -1) {
                        final ApiResponse apiResponse = GSON.fromJson(body.string(), ApiResponse.class);
                        callBack.onfail(apiResponse.message);
                    }else {
                        callBack.onSuccess(body.string());
                    }
                    body.close();
                } catch (IOException e) {
//            throw new RuntimeException(e);
                    callBack.onfail(e.toString());
                }
            }
        }).start();

    }
}
