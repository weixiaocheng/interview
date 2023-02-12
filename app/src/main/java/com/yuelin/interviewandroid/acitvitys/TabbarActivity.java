package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.components.TabbarItem;
import com.yuelin.interviewandroid.fragment.CollectFragment;
import com.yuelin.interviewandroid.fragment.HomeFragment;
import com.yuelin.interviewandroid.fragment.MineFragment;
import com.yuelin.interviewandroid.fragment.NewsFragment;

import java.util.ArrayList;

public class TabbarActivity extends BaseActivity implements View.OnClickListener {
    private TabbarItem tabHome, tabNews, tabCollect, tabMine;
    private ArrayList<TabbarItem> tabbarList;

    private int[] tabIds = {
            R.id.home_tabbar_home,
            R.id.home_tabbar_news,
            R.id.home_tabbar_collect,
            R.id.home_tabbar_mine};

    /**
     * fragment 列表
     */
    private HomeFragment home_f = new HomeFragment();
    private NewsFragment news_f = new NewsFragment();
    private CollectFragment collect_f = new CollectFragment();
    private MineFragment mine_f = new MineFragment();

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
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.home_view_page, home_f)
                .add(R.id.home_view_page, news_f)
                .add(R.id.home_view_page, collect_f)
                .add(R.id.home_view_page, mine_f)
                .show(home_f)
                .hide(news_f)
                .hide(collect_f)
                .hide(mine_f)
                .commit();
    }

    private void clearAll() {
        this.getSupportFragmentManager().beginTransaction().hide(home_f).hide(news_f).hide(collect_f).hide(mine_f).commit();
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick: " + view);
        clearAll();
        Fragment tempF = null;
        for (TabbarItem tabitem : tabbarList
        ) {
            tabitem.setSelected(view == tabitem);
            switch (view.getId()) {
                case R.id.home_tabbar_home:
                    tempF = home_f;
                    break;
                case R.id.home_tabbar_news:
                    tempF = news_f;
                    break;
                case R.id.home_tabbar_collect:
                    tempF = collect_f;
                    break;
                case R.id.home_tabbar_mine:
                    tempF = mine_f;
                    break;
            }
        }
        this.getSupportFragmentManager().beginTransaction().show(tempF).commit();
    }
}