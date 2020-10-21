package com.zr.wanandroid.common.net.bean;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    public T data;
    public String errorCode;
    public String errorMsg;
}
