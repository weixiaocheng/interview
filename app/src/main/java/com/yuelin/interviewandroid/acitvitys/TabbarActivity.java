package com.yuelin.interviewandroid.acitvitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.components.TabbarItem;

import java.util.ArrayList;
import java.util.List;

public class TabbarActivity extends BaseActivity {
    private TabbarItem tabHome, tabNews, tabCollect, tabMine;
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar);
        init();
    }

    private void init() {
        tabHome = (TabbarItem) findViewById(R.id.home_tabbar_home);
        tabNews = (TabbarItem) findViewById(R.id.home_tabbar_news);
        tabCollect = (TabbarItem) findViewById(R.id.home_tabbar_collect);
        tabMine = (TabbarItem) findViewById(R.id.home_tabbar_mine);
        tabHome.setSelected(true);
    }
}