package com.yuelin.interviewandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryRespone {
    public boolean isError;
    public String message;
    public int code;
    // 先初始化一下 避免后期可能为空
    public ArrayList<CategoryRespone.CategoryBeanItem> data = new ArrayList<>();

    public class CategoryBeanItem {
        /**
         * 分类名称
         */
        public String name;
        /**
         * 分类id
         */
        @SerializedName("id")
        public int categoryId;
        /**
         * 分类图标
         */
        public String icon;
    }
}
