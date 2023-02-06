package com.yuelin.interviewandroid.acitvitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yuelin.interviewandroid.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private Timer timer;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startLogin();
            }
        }, 2000);
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