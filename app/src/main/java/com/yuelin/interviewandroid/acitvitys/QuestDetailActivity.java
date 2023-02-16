package com.yuelin.interviewandroid.acitvitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.views.NavigationBar;

public class QuestDetailActivity extends AppCompatActivity {

    private NavigationBar navigationBar;
    private WebView webView;
    // 问题id
    private int questId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_detail);
        init();
    }

    private void init() {
        navigationBar = findViewById(R.id.nav_bar);
        navigationBar.setBackIconOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // 获取传参
        Intent intent = getIntent();
        navigationBar.setTitle(intent.getStringExtra("title"));
        questId = intent.getIntExtra("questId", 0);
        loadData();
    }

    private void loadData() {

    }
}