package com.yuelin.interviewandroid.network;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.model.ApiResponse;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkManager {

    public static NetworkManager getInstance = new NetworkManager();

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new  OkHttpClient().newBuilder()
            .cache(new Cache(new File("fos.txt"), 10 * 1024 * 1024)) //看下分析Cache的构造
            .build();


    public interface HttpCallbackListener {
        void onFinish(String response);
        void onError(Exception e);
    }

    public static void get(String url, HttpCallbackListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.onFinish(response.body().string());
            }
        });
    }

    public static void post(String url, String json, HttpCallbackListener listener) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    listener.onFinish(response.body().string());
                }else {
                    listener.onError(new Exception(response.message()));
                }
            }
        });
    }

    /**
     * 获取新闻列表
     * @param pageIndex 当前的页面
     * @param listener 成功失败的回调
     */
    public void getNews( int pageIndex, HttpCallbackListener listener) {
        String url = ApiConfig.base_url + ApiConfig.api_news + "?page_index=" + pageIndex;
        get(url, listener);
    }

    /**
     * 获取首页分类列表
     * @param listener
     */
    public void getCategoryList( HttpCallbackListener listener) {
        String url = ApiConfig.base_url + ApiConfig.api_categroylist;
        get(url, listener);
    }

    /**
     * 获取分类之后的 显示list 数据
     * @param pageIndex
     * @param cateId
     * @param listener
     */
    public void getCategoryListWithCateid(int pageIndex, String cateId, HttpCallbackListener listener) {
        String url = ApiConfig.base_url + ApiConfig.api_getListWithCateid + "?page_index=" + String.valueOf(pageIndex) + "&&id=" + cateId;
        get(url, listener);
    }

    /**
     * 获取问题详情
     * @param questId
     * @param listener
     */
    public void getQuestDetail(int questId, HttpCallbackListener listener) {
        String url = ApiConfig.base_url + ApiConfig.api_getQuestDetail + "?id=" + questId;
        get(url, listener);
    }
}