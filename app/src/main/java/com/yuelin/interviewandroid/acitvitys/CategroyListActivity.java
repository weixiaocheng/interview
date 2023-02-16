package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.CateListRespone;
import com.yuelin.interviewandroid.network.NetworkManager;
import com.yuelin.interviewandroid.views.NavigationBar;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CategroyListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listView;
    private NavigationBar navigationBar;

    private int pageIndex = 0;

    private int categoryId;

    private Gson gson = new Gson();

    private ArrayList<CateListRespone.BeanItem> list = new ArrayList<CateListRespone.BeanItem>();

    private ListAdapter adapter;

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
        listView.setOnItemClickListener(this);
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

        adapter = new ListAdapter();
        listView.setAdapter(adapter);
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
                // 刷新列表
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // 跳转到下一页
        CateListRespone.BeanItem item = list.get(i);
        Intent intent = new Intent(this, QuestDetailActivity.class);
        intent.putExtra("title", item.title);
        intent.putExtra("questId", item.questId);
        startActivity(intent);
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
            CateListRespone.BeanItem beanItem = list.get(i);
            HanlderView hanlderView;
            if (view == null) {
                view = LayoutInflater.from(CategroyListActivity.this).inflate(R.layout.list_quest_item, viewGroup, false);
                 hanlderView = new HanlderView();
                hanlderView.init(view);
                view.setTag(hanlderView);
            }else {
                hanlderView = (HanlderView) view.getTag();
            }
            hanlderView.titleTextV.setText(beanItem.title);
            hanlderView.onTextV.setText("NO."+i);
            return view;
        }
    }

    private class HanlderView {
        public TextView onTextV;
        public TextView titleTextV;
        public void init(View view) {
            this.onTextV = view.findViewById(R.id.quest_id);
            titleTextV = view.findViewById(R.id.quest_title_id);
        }
    }
}