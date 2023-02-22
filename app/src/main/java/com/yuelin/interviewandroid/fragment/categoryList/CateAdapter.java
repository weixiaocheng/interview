package com.yuelin.interviewandroid.fragment.categoryList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.model.CateListRespone;

import java.util.ArrayList;
import java.util.List;

public class CateAdapter extends BaseAdapter {

    private List<CateListRespone.BeanItem> list;

    public void setList(List<CateListRespone.BeanItem> list) {
        this.list = list;
    }

    private Context mContext;

    public static CateAdapter init( Context mContext) {
        CateAdapter adapter = new CateAdapter();

        adapter.list = new ArrayList<>();

        adapter.mContext = mContext;
        return adapter;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).questId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        CateHandleView cateHandleView;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_quest_item, parent, false);
            cateHandleView = CateHandleView.init(view);
            view.setTag(cateHandleView);
        }else {
            view = convertView;
            cateHandleView = (CateHandleView) view.getTag();
        }
        CateListRespone.BeanItem item = list.get(position);
        cateHandleView.NoLb.setText("No" + position + ".");
        cateHandleView.titleLb.setText(item.title);
        return view;
    }
}
