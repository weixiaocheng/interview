package com.yuelin.interviewandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.NewsResponse;

import java.util.List;

public class NewsFragment extends Fragment {

    private List<NewsResponse.BeanNewItem> listData;
    public ListView listView;

    // 使用单行列表 够用了
    private RecyclerView.Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }


    /**
     * 设置list里面的数据
     * @param list
     */
    public void setListData(List<NewsResponse.BeanNewItem> list) {
        listData = list;
        // 实现刷新逻辑
        adapter.notifyDataSetChanged();
    }
}
