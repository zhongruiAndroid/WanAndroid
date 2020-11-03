package com.zr.wanandroid.common.listener;

public interface RequestListener<T> {
    void onSuccess(T data);
    void onError(String code, String errorMsg);
}
