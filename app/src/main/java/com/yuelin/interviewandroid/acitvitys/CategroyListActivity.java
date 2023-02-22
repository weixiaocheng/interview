package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.fragment.categoryList.CategoryItemClickCallBack;
import com.yuelin.interviewandroid.fragment.categoryList.CategoryListFragment;
import com.yuelin.interviewandroid.fragment.categoryList.QuestDetailFragment;
import com.yuelin.interviewandroid.model.CateListRespone;


public class CategroyListActivity extends AppCompatActivity implements CategoryItemClickCallBack {



    private CategoryListFragment cateFragment;

    private QuestDetailFragment questDetailFragment;
    private int categoryId;
    private String categoryName;

    private CategoryItemClickCallBack itemClickCallBack;

    public QuestDetailFragment getQuestDetailFragment() {
        if (questDetailFragment == null ){
            questDetailFragment = new QuestDetailFragment();
            questDetailFragment.setItemClickCallBack(this);
        }
        return questDetailFragment;
    }

    public CategoryListFragment getCateFragment() {
        if (cateFragment == null ){
            cateFragment = CategoryListFragment.init(String.valueOf(categoryId), categoryName, this) ;
            cateFragment.setItemClickCallBack(this);
        }
        return cateFragment;
    }
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
        getSupportFragmentManager()
                .beginTransaction()
                .add( R.id.fragment_content,getCateFragment())
                .add(R.id.fragment_content, getQuestDetailFragment())
                .hide(getQuestDetailFragment())
                .show(cateFragment)
                .commit();
    }

    @Override
    public void itemOnClick(CateListRespone.BeanItem item) {
        if (item == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(getQuestDetailFragment())
                    .show(getCateFragment())
                    .commit();
        }else {
            getQuestDetailFragment().setQuestId(item.questId);
            getQuestDetailFragment().setTitle(item.title);
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(getCateFragment())
                    .show(getQuestDetailFragment())
                    .commit();
        }
    }
}