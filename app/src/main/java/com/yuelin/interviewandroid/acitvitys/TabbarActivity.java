package com.yuelin.interviewandroid.acitvitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yuelin.interviewandroid.R;

import java.util.ArrayList;
import java.util.List;

public class TabbarActivity extends BaseActivity {

    private ViewPager viewPager;

    static final int[] layoutList = {
            R.layout.view_frist,
            R.layout.view_second,
            R.layout.view_third
    };
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar);
        init();
    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.home_view_page);

        views = new ArrayList<>();
        for (int index = 0; index < layoutList.length; index++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.welcome_bg);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(imageView);
        }

        viewPager.setAdapter(adapter);
    }

    PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return layoutList.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View child = views.get(position);
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View child = views.get(position);
            container.removeView(child);
        }
    };
}