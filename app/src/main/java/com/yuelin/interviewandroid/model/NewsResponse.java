package com.yuelin.interviewandroid.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsResponse {
    public boolean isError;
    public String message;
    public int code;
    // 先初始化一下 避免后期可能为空
    public ArrayList<BeanNewItem> data = new ArrayList<>();

    public class BeanNewItem {
        @SerializedName("id")
        public int newsId;
        public String title;
        @SerializedName("sub_title")
        public String subTitle;
        @SerializedName("url_link")
        public String urlLink;
        @SerializedName("image_url")
        public String imgUrl;
    }
}
