package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.components.TabbarItem;

import java.util.ArrayList;
import java.util.List;

public class TabbarActivity extends BaseActivity implements View.OnClickListener {
    private TabbarItem tabHome, tabNews, tabCollect, tabMine;
    private ViewPager viewPager;
    private ArrayList<TabbarItem> tabbarList;

    private int[] tabIds = {
            R.id.home_tabbar_home,
            R.id.home_tabbar_news,
            R.id.home_tabbar_collect,
            R.id.home_tabbar_mine};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar);
        init();
    }

    private void init() {
        tabbarList = new ArrayList<TabbarItem>();
        for (int index = 0; index < tabIds.length; index++) {
            TabbarItem tabbarItem = findViewById(tabIds[index]);
            tabbarList.add(tabbarItem);
            if (index == 0) {
                tabbarItem.setSelected(true);
            }
            tabbarItem.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick: " + view);
        for (TabbarItem tabitem : tabbarList
        ) {
            tabitem.setSelected(view == tabitem);
        }
    }
}