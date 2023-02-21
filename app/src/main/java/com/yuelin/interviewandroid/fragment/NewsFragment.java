package com.yuelin.interviewandroid.fragment;

import static com.yuelin.interviewandroid.acitvitys.MainActivity.mgson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.fragment.newsComponents.NewsAdapter;
import com.yuelin.interviewandroid.model.NewsResponse;
import com.yuelin.interviewandroid.network.NetworkManager;


public class NewsFragment extends Fragment {

    private GridView gridView;
    private NewsAdapter adapter;

    public NewsAdapter getAdapter() {
        if (adapter == null) {
            adapter = NewsAdapter.getInstance(getContext());
        }
        return adapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        loaddata();
        return view;
    }

    private void initView(View view) {
        gridView = (GridView) view.findViewById(R.id.news_grid_content_view);
        gridView.setAdapter(getAdapter());
        gridView.setBackgroundColor(0xFFF7F7F7);
    }

    private void loaddata() {
        NetworkManager.getInstance.getNews(0, new NetworkManager.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                NewsResponse newsResponse = mgson.fromJson(response, NewsResponse.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (gridView.getAdapter() == null) {
                            gridView.setAdapter(getAdapter());
                        }
                            adapter.setDatasoure(newsResponse.data);

                    }
                });
            }

            @Override
            public void onError(Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        });
    }
}
