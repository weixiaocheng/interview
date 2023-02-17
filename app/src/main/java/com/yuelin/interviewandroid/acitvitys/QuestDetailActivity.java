package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.QuestDetailRespose;
import com.yuelin.interviewandroid.network.NetworkManager;
import com.yuelin.interviewandroid.views.NavigationBar;

import java.util.Base64;

public class QuestDetailActivity extends AppCompatActivity {

    private NavigationBar navigationBar;
    private WebView webView;
    // 问题id
    private int questId;

    private Gson gson = new Gson();

    private String html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_detail);
        init();
    }

    private void init() {
        navigationBar = findViewById(R.id.nav_bar);
        webView = findViewById(R.id.web_view_id);
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

    private String getHtmlData(String bodyHTML) {
        String  head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                +
                "<style>img{max-width: 100%; width:auto; height:auto;} body{word-break:break-all;margin:30px}</style>"
                +
                "</head>";

        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }


    private void loadData() {
        NetworkManager.getInstance.getQuestDetail(questId, new NetworkManager.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                QuestDetailRespose questDetailRespose = gson.fromJson(response, QuestDetailRespose.class);
                String base64Str = questDetailRespose.data.html;
                byte[] decodedBytes = Base64.getDecoder().decode(base64Str);
                html = new String(decodedBytes);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadData(getHtmlData(html), "text/html;charset=utf-8", "utf-8");
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(QuestDetailActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}