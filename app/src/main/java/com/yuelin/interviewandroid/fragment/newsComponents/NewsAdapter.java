package com.yuelin.interviewandroid.fragment.newsComponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.NewsResponse;
import com.yuelin.interviewandroid.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class NewsAdapter extends BaseAdapter {

    private Context mContent;

    public static NewsAdapter getInstance(Context context) {
        NewsAdapter adapter = new NewsAdapter();
        adapter.mContent = context;
        return adapter;
    }

    private List<NewsResponse.BeanNewItem> datasoure;

    public void setDatasoure(List<NewsResponse.BeanNewItem> datasoure) {
        if (datasoure.size() == 0) {
            return;
        }
        this.datasoure = datasoure;
    }

    public List<NewsResponse.BeanNewItem> getDatasoure() {
        if (datasoure == null) {
            datasoure = new ArrayList<NewsResponse.BeanNewItem>();
        }
        return datasoure;
    }

    public void pushDatasoure(List<NewsResponse.BeanNewItem> datasoure) {
        if (datasoure.size() == 0) {
            return;
        }
        List<NewsResponse.BeanNewItem> list = getDatasoure();
        list.addAll(datasoure);
        setDatasoure(list);
        // 刷新数据
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return getDatasoure().size();
    }

    @Override
    public Object getItem(int i) {
        return getDatasoure().get(i);
    }

    @Override
    public long getItemId(int i) {
        return getDatasoure().get(i).newsId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewsItemCell cell;
        if (view == null) {
            view = LayoutInflater.from(mContent).inflate(R.layout.news_item_cell, viewGroup, false);
            cell = new NewsItemCell(mContent, view);
            view.setTag(cell);
        }else {
            cell = (NewsItemCell) view.getTag();
        }
        NewsResponse.BeanNewItem item = datasoure.get(i);
        cell.setImageViewUrl(Utils.base64toString(item.imgUrl));
        cell.setTitleLbText(item.title);
        cell.setSubTitleLbText(item.subTitle);
        return view;
    }
}

