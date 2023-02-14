package com.yuelin.interviewandroid.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    public boolean isError;
    public String message;
    public int code;
    public List<BeanNewItem> data;

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
