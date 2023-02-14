package com.yuelin.interviewandroid.network;

public interface ICallBack<T> {
    /**
     * 请求成功的返回
     *
     * @param data 可能为空
     */
    public void onSuccess(T data);

    /**
     * 请求失败返回 错误信息
     *
     * @param msg
     */
    public void onfail(String msg);
}


