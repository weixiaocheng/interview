package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.NewsResponse;
import com.yuelin.interviewandroid.network.ApiConfig;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final Gson mgson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init() ;
    }

    private void init() {
        Request.Builder builder = new Request.Builder();
        // 先写死
        builder.url(ApiConfig.base_url + ApiConfig.api_news + "?page_index=0");
        Request request = builder.build();
        OkHttpClient client =  new OkHttpClient();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                NewsResponse response1 = mgson.fromJson(response.body().string(), NewsResponse.class);
                Log.i(TAG, "onResponse: " + response1);
            }
        });
    }

}