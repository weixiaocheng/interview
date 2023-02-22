package com.yuelin.interviewandroid.acitvitys;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuelin.interviewandroid.R;
import com.yuelin.interviewandroid.fragment.categoryList.CategoryItemClickCallBack;
import com.yuelin.interviewandroid.fragment.categoryList.CategoryListFragment;
import com.yuelin.interviewandroid.fragment.categoryList.QuestChangeLisenter;
import com.yuelin.interviewandroid.fragment.categoryList.QuestDetailFragment;
import com.yuelin.interviewandroid.model.CateListRespone;


public class CategroyListActivity extends AppCompatActivity implements CategoryItemClickCallBack, QuestChangeLisenter {



    private CategoryListFragment cateFragment;

    private QuestDetailFragment questDetailFragment;
    private int categoryId;
    private String categoryName;

    private CategoryItemClickCallBack itemClickCallBack;

    public QuestDetailFragment getQuestDetailFragment() {
        if (questDetailFragment == null ){
            questDetailFragment = new QuestDetailFragment();
            questDetailFragment.setItemClickCallBack(this);
            questDetailFragment.setQuestChangeLisenter(this);
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
    public void itemOnClick(CateListRespone.BeanItem item, int postion) {
        if (item == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(getQuestDetailFragment())
                    .show(getCateFragment())
                    .commit();
        } else {
            getQuestDetailFragment().setCurrentIndex(postion);
            getQuestDetailFragment().setQuestId(item.questId);
            getQuestDetailFragment().setTitle(item.title);
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(getCateFragment())
                    .show(getQuestDetailFragment())
                    .commit();
        }
    }

    @Override
    public void nextQuest() {
        int index = getQuestDetailFragment().getCurrentIndex();
        if (index >= getCateFragment().getList().size() - 1) {
            if (getCateFragment().isHasMore()) {
                Toast.makeText(this, "加载中", Toast.LENGTH_SHORT).show();
                getCateFragment().loadData();
            }
            return;
        }

        index ++;
        CateListRespone.BeanItem beanItem = getCateFragment().getList().get(index);
        getQuestDetailFragment().setCurrentIndex(index);
        getQuestDetailFragment().setQuestId(beanItem.questId);
        getQuestDetailFragment().setTitle(beanItem.title);
    }

    @Override
    public void previousQuestion() {
        int index = getQuestDetailFragment().getCurrentIndex();
        if (index == 0) {
            return;
        }
        index = index - 1;
        CateListRespone.BeanItem beanItem = getCateFragment().getList().get(index);
        getQuestDetailFragment().setCurrentIndex(index);
        getQuestDetailFragment().setQuestId(beanItem.questId);
        getQuestDetailFragment().setTitle(beanItem.title);
    }
}