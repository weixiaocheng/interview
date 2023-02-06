package com.yuelin.interviewandroid.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuelin.interviewandroid.R;

import java.util.zip.Inflater;

public class NavigationBar extends RelativeLayout {
    /**
     * @title 导航栏标题
     */
    private TextView titleView;
    /**
     * @title 返回按钮
     */
    private ImageView backIcon;
    /**
     * @title 右侧控件,
     * 这里是预留做扩展的 使用的时候主要控制右侧的空间的大小
     */
    private ViewGroup rightViewContent;


    public NavigationBar(Context context) {
        super(context);
        init(context, null);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        // 找到对应的view
        View view = LayoutInflater.from(context).inflate(R.layout.view_navigation, this, false);
        titleView = view.findViewById(R.id.nav_title);
        backIcon = view.findViewById(R.id.back_icon);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NavigationBar);
        boolean isBack = typedArray.getBoolean(R.styleable.NavigationBar_is_back, false);
        backIcon.setVisibility(isBack ? View.VISIBLE : View.GONE);

        String title = typedArray.getString(R.styleable.NavigationBar_title);
        titleView.setText(title);

        addView(view);
    }

    /**
     * @title 设置nav的标题
     * @param title
     */
    public void setTitle(String title) {
        titleView.setText(title);
    }

    /**
     * @title 设置是否有返回按钮
     * @param isBack
     */
    public void setIsBack(boolean isBack) {
        backIcon.setVisibility(isBack? View.VISIBLE : View.GONE);
    }




}
