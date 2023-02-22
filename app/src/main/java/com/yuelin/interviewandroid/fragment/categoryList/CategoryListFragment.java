package com.yuelin.interviewandroid.fragment.categoryList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.fragment.BaseFragment;
import com.yuelin.interviewandroid.model.CateListRespone;
import com.yuelin.interviewandroid.network.NetworkManager;
import com.yuelin.interviewandroid.views.NavigationBar;

import java.util.ArrayList;
import java.util.List;

public class CategoryListFragment extends BaseFragment {

    /* UI区 */
    private NavigationBar navigationBar;
    private ListView listView;

    /* 数据区 */
    private Gson mgson = new Gson();

    private String categoryId;
    // 从0开始算页面
    private int currentIndex = 0;

    public void contactList(List<CateListRespone.BeanItem> list) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.addAll(list);
        if (callBack != null) {
            callBack.callBackList(this.list);
        }
    }


    // 数据列表
    private List<CateListRespone.BeanItem> list = new ArrayList<>();

    // 每次刷新数据之后回调一下总数据到activity里面, 这里需要处理 显示详情的逻辑
    private CallBackCategoryListData callBack;
    private boolean hasMore;
    private boolean isLoading;

    public static CategoryListFragment init(String categoryId, CallBackCategoryListData callBack) {
        CategoryListFragment fragment = new CategoryListFragment();
        fragment.categoryId = categoryId;
        fragment.callBack = callBack;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categroylist, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 确保第一次永远为0, 界面不会有横竖屏的切换
        currentIndex = 0;
        // 最开始的数据加载
        loadData();
    }

    private void initView(View view) {
        navigationBar = view.findViewById(R.id.nav_bar);
        listView = view.findViewById(R.id.category_list_view);
    }

    // 数据加载
    private void loadData() {
        // 避免重复加载
        if (isLoading) {
            return;
        }
        isLoading = true;
        NetworkManager.getInstance.getCategoryListWithCateid(currentIndex, categoryId, new NetworkManager.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                isLoading = false;

                CateListRespone mR = mgson.fromJson(response, CateListRespone.class);
                if (mR.isError != false) {
                    return;
                }
                if (mR.data.size() == 0) {
                    hasMore = false;
                    showToastWithMsg("没有更多了");
                    return;
                }
                contactList(mR.data);
                hasMore = true;
                currentIndex++;
            }

            @Override
            public void onError(Exception e) {
                isLoading = false;
                showToastWithMsg(e.toString());
            }
        });
    }
}
