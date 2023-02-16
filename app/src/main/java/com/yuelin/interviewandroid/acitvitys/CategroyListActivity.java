package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.CateListRespone;
import com.yuelin.interviewandroid.network.NetworkManager;
import com.yuelin.interviewandroid.views.NavigationBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CategroyListActivity extends AppCompatActivity {

    private ListView listView;
    private NavigationBar navigationBar;

    private int pageIndex = 0;

    private int categoryId;

    private Gson gson = new Gson();

    private ArrayList<CateListRespone.BeanItem> list = new ArrayList<CateListRespone.BeanItem>();

    public void setTitle(String title) {
        this.title = title;
        navigationBar.setTitle(title);
    }

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categroy_list);
        init() ;
    }

    private void init() {
        listView = (ListView) findViewById(R.id.category_list_view);
        navigationBar = findViewById(R.id.nav_bar);
        navigationBar.setIsBack(true);
        navigationBar.setBackIconOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 返回上一页
                onBackPressed();
            }
        });

        // 获取传参
        Intent intent = getIntent();
        if (intent != null) {
             categoryId = intent.getIntExtra("categoryId", 1) ;
             setTitle(intent.getStringExtra("categoryName"));
        }
        loadData();
    }

    // 加载数据
    private void loadData() {


        NetworkManager.getInstance.getCategoryListWithCateid(pageIndex, String.valueOf(categoryId), new NetworkManager.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                CateListRespone cateItemRes = gson.fromJson(response, CateListRespone.class);
                if (pageIndex == 0 ) {
                    list = cateItemRes.data;
                }else {
                    list.addAll(cateItemRes.data);
                }

                if (cateItemRes.data.size() > 0 ) {
                    pageIndex ++;
                }

                // 设置数据
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CategroyListActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return list.get(i).questId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}