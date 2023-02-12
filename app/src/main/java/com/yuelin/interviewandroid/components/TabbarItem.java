package com.yuelin.interviewandroid.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuelin.interviewandroid.R;

public class TabbarItem extends RelativeLayout {
    /// 定义变量
    private int tabIcon;
    private String tabTitle;
    private int tabColor;
    /// 定义布局对象
    private ImageView iconImv;
    private TextView titleLabel;
    private View mView;

    public TabbarItem(Context context) {
        super(context);
        init(context, null);
    }

    public TabbarItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TabbarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public TabbarItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    @SuppressLint("ResourceAsColor")
    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabbarItem);
        tabIcon = typedArray.getResourceId(R.styleable.TabbarItem_tab_icon, R.mipmap.home_icon);
        tabTitle = typedArray.getString(R.styleable.TabbarItem_tab_title);
        tabColor = typedArray.getColor(R.styleable.TabbarItem_tab_title_color, R.color.tabBarColorH);

        mView = LayoutInflater.from(context).inflate(R.layout.item_tabbar, this, false);

        iconImv = mView.findViewById(R.id.tab_icon);
        titleLabel = mView.findViewById(R.id.tab_title);

        iconImv.setImageResource(tabIcon);
        titleLabel.setText(tabTitle);
        titleLabel.setTextColor(tabColor);

        addView(mView);
    }
}
