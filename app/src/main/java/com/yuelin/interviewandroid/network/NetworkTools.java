package com.yuelin.interviewandroid.network;

import android.widget.Toast;

import com.yuelin.interviewandroid.utils.Utils;

public class NetworkTools {
    public static NetworkTools instance = new NetworkTools();

    /**
     * 获取新闻列表
     * @param pageIndex
     */
    public void getNews(int pageIndex, ICallBack callBack) {
        String url = ApiConfig.base_url + ApiConfig.api_news;
        url = url + "?page_index=" + pageIndex;
        NetworkManager.getInstance.dioGet(url, new ICallBack() {
            @Override
            public void onSuccess(Object data) {
                // 需要序列化一下了
            }

            @Override
            public void onfail(String msg) {
                callBack.onfail(msg);
            }
        });
    }
}
