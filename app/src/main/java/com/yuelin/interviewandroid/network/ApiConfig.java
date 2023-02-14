package com.yuelin.interviewandroid.network;

public class ApiConfig {
    /**
     * 当前项目的运行状态, 是否为调试中, 发版之前需要修改回来为 release
     */
    private static boolean isDebug = true;

    /// 网络请求地址
    public static final String base_url = isDebug ? "http://192.168.2.103:8080/api/" : "http://api.yuelin.link/api/";

    /// 用户登录 使用post 请求
    public static final String api_login = "login";

    /// 新闻列表
    public static final String api_news = "getNews";
}
