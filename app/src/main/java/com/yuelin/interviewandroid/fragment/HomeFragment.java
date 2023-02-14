package com.yuelin.interviewandroid.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.CategoryRespone;
import com.yuelin.interviewandroid.network.NetworkManager;

public class HomeFragment extends Fragment {

    private Gson mgson = new Gson();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void init() {
        // 获取网络请求
        loadData();
    }

    private void loadData() {
        NetworkManager.getInstance.getCategoryList(new NetworkManager.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                CategoryRespone categoryRespone = mgson.fromJson(response, CategoryRespone.class);
                Log.i(TAG, "onFinish: " + categoryRespone.data.size());
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
