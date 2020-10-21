package com.zr.wanandroid.common.listener;

public interface RequestListener<T> {
    void onSuccess(T obj);
    void onError(String code, String errorMsg);
}
