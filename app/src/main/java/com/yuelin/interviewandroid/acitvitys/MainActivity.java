package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.NewsResponse;
import com.yuelin.interviewandroid.network.ApiConfig;
import com.yuelin.interviewandroid.network.NetworkManager;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final Gson mgson = new Gson();

    private List<NewsResponse.BeanNewItem> news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        NetworkManager.getInstance.getNews(0, new NetworkManager.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                NewsResponse newsResponse = mgson.fromJson(response, NewsResponse.class);
                news = newsResponse.data;
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}