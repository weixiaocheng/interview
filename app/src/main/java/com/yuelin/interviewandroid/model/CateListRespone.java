package com.yuelin.interviewandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CateListRespone {
    public boolean isError;
    public String message;
    public int code;
    // 先初始化一下 避免后期可能为空
    public ArrayList<CateListRespone.BeanItem> data = new ArrayList<>();

    public class BeanItem {

        @SerializedName("id")
        public int questId;
        /**
         * 题目标题
         */
        public String title;
    }
}
