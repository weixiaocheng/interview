package com.yuelin.interviewandroid.fragment.categoryList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
    private View footView, emptyView;

    /* 数据区 */
    private Gson mgson = new Gson();
    private int visibleLastIndex = 0; // 最后的可视项索引

    // 分类id
    private String categoryId;
    // 分类的名称
    private String categoryName;
    // 从0开始算页面
    private int currentIndex = 0;
    private CateAdapter adapter;

    public void setItemClickCallBack(CategoryItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    private CategoryItemClickCallBack itemClickCallBack;

    public void contactList(List<CateListRespone.BeanItem> list) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.addAll(list);

        adapter.setList(this.list);
        adapter.notifyDataSetChanged();
    }


    // 数据列表
    private List<CateListRespone.BeanItem> list = new ArrayList<>();

    public List<CateListRespone.BeanItem> getList() {
        return list;
    }

    // 每次刷新数据之后回调一下总数据到activity里面, 这里需要处理 显示详情的逻辑
    private boolean hasMore;

    public boolean isHasMore() {
        return hasMore;
    }

    private boolean isLoading;

    public static CategoryListFragment init(String categoryId, String categoryName, CategoryItemClickCallBack itemClickCallBack) {
        CategoryListFragment fragment = new CategoryListFragment();
        fragment.categoryId = categoryId;
        fragment.categoryName = categoryName;
        fragment.itemClickCallBack = itemClickCallBack;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categroylist, container, false);
        footView = LayoutInflater.from(getContext()).inflate(R.layout.list_load_more_view, container, false);
        initView(view);
        currentIndex = 0;
        adapter = CateAdapter.init(getContext());
        listView.setAdapter(adapter);
        // 最开始的数据加载
        loadData();
        initEvent();
        return view;
    }

    private void initView(View view) {
        navigationBar = view.findViewById(R.id.nav_bar);
        navigationBar.setTitle(categoryName);
        navigationBar.setIsBack(true);
        navigationBar.setBackIconOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回上一页
                getActivity().onBackPressed();
            }
        });
        listView = view.findViewById(R.id.category_list_view);
        listView.addFooterView(footView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (itemClickCallBack != null) {
                    itemClickCallBack.itemOnClick(list.get(position), position);
                }
            }
        });
    }

    private void initEvent() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引
                int lastIndex = itemsLastIndex + 1; // 加上底部的loadMoreView项

                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex && hasMore) {
                    // 这里放置异步加载数据的代码
                    loadData();
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
            }
        });
    }

    // 数据加载
    public void loadData() {
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (listView.getFooterViewsCount() > 0) {
                                listView.removeFooterView(footView);
                            }
                        }
                    });
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contactList(mR.data);
                        hasMore = true;
                        currentIndex++;
                        if (listView != null && listView.getFooterViewsCount() == 0) {
                            listView.addFooterView(footView);
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                isLoading = false;
                showToastWithMsg(e.toString());
            }
        });
    }
}
