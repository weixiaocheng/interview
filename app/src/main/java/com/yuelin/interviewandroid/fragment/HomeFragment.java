package com.yuelin.interviewandroid.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.acitvitys.TabbarActivity;
import com.yuelin.interviewandroid.model.CategoryRespone;
import com.yuelin.interviewandroid.network.NetworkManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

public class HomeFragment extends Fragment {

    private Gson mgson = new Gson();

    private ListView listView;

    /**
     * 列表的adapter
     */
    private HomeListAdapater adapter;

    public HomeListAdapater getAdapter() {
        if (adapter == null) {
            adapter = new HomeListAdapater( list, getContext());
        }
        return adapter;
    }

    private List<CategoryRespone.CategoryBeanItem> list;
    private View inflate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_home, container, false);
        listView = mView.findViewById(R.id.home_list_view);
//        listView.setAdapter(getAdapter());
        return mView;
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
                list = categoryRespone.data;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (listView.getAdapter() == null) {
                            listView.setAdapter(getAdapter());
                        }else {
                            adapter.setList(list);
                            // 刷新数据
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public class HomeListAdapater extends BaseAdapter {

        private List<CategoryRespone.CategoryBeanItem> list;

        public void setList(List<CategoryRespone.CategoryBeanItem> list) {
            this.list = list;
        }

        private Context mContext;

        public HomeListAdapater (List<CategoryRespone.CategoryBeanItem> list, Context mContext) {
            this.list = list;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            CategoryRespone.CategoryBeanItem categoryBeanItem = list.get(i);
            return categoryBeanItem.categoryId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            CategoryRespone.CategoryBeanItem item = list.get(i);
            HanderView handerView;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_item,viewGroup,false);
                ImageView img_icon = (ImageView) view.findViewById(R.id.home_item_icon);
                TextView txt_title = (TextView) view.findViewById(R.id.home_item_title);
                handerView = new HanderView();
                handerView.titleView = txt_title;
                handerView.imageView = img_icon;
                view.setTag(handerView);
            }else {
                handerView = (HanderView) view.getTag();
            }
            handerView.titleView.setText(item.name);
            return view;
        }

        private class HanderView {
            ImageView imageView;
            TextView titleView;
        }
    }
}
