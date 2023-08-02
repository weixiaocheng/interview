package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.network.ApiConfig;
import com.yuelin.interviewandroid.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private Timer timer;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 在这里检测网络配置网络
        // http://192.168.1.5:8100/api 本地服务器
//        Utils.isNetOnline("http://192.168.1.5:8100/api", new Comparable<Boolean>() {
//            @Override
//            public int compareTo(Boolean aBoolean) {
//                ApiConfig.base_url = aBoolean ? "http://192.168.1.5:8100/api/" : "http://192.168.2.116:8100/api/";
//                Log.e(TAG, "compareTo: ".concat(aBoolean ? "链接成功" : "不可以访问"));
//                startLogin();
//                return 0;
//            }
//        });
        startLogin();
    }

    private void init() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startLogin();
            }
        }, 1000);
    }

    private void startLogin() {
        Intent intent = new Intent(this, TabbarActivity.class);
        startActivity(intent);
        finish();
    }
}