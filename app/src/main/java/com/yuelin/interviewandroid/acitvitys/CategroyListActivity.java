package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.fragment.categoryList.CategoryListFragment;



public class CategroyListActivity extends AppCompatActivity  {

    private CategoryListFragment cateFragment;
    private int categoryId;
    private String categoryName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categroy_list);
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId", 0);
        categoryName = intent.getStringExtra("categoryName");
        init();
    }

    private void init() {
        cateFragment = CategoryListFragment.init(String.valueOf(categoryId), categoryName) ;
        getSupportFragmentManager().beginTransaction().add( R.id.fragment_content,cateFragment).show(cateFragment).commit();
    }
}