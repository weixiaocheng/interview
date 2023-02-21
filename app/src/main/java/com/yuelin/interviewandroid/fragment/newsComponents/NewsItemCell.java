package com.yuelin.interviewandroid.fragment.newsComponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.yuelin.interviewandroid.R;

public class NewsItemCell  {

    private TextView titleLb, subTitleLb;
    private ImageView imageView;


    public NewsItemCell(Context context, View view) {
        imageView = view.findViewById(R.id.news_item_cell_img);
        titleLb = view.findViewById(R.id.news_item_cell_title);
        subTitleLb = view.findViewById(R.id.news_item_cell_sub_title);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }



    public void setTitleLbText(String title) {
        titleLb.setText(title);
    }

    public void setSubTitleLbText(String subTitleLbText) {
        subTitleLb.setText(subTitleLbText);
    }

    public void setImageViewUrl(String url) {
        Glide.with(imageView).load(url).centerCrop().into(imageView);
    }
}
