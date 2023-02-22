package com.yuelin.interviewandroid.fragment.categoryList;

import com.yuelin.interviewandroid.model.CateListRespone;

import java.util.List;

public interface CallBackCategoryListData {
    // 返回当前更新的列表数量
    public void callBackList(List<CateListRespone.BeanItem> list) ;
}
