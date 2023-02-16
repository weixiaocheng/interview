package com.yuelin.interviewandroid.model;

import java.util.ArrayList;

public class QuestDetailRespose {
    public boolean isError;
    public String message;
    public int code;
    // 先初始化一下 避免后期可能为空
    public BeanNewItem data;

    public class BeanNewItem {
        public String html;
    }
}
