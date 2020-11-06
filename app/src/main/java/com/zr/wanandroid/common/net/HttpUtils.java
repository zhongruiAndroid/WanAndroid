package com.zr.wanandroid.common.net;

import com.github.theokhttp.TheOkHttp;
import com.github.theokhttp.TheOkRequestBuilder;

import java.util.Map;

public class HttpUtils {
    public static TheOkRequestBuilder get() {
        return get(null);
    }
    public static TheOkRequestBuilder get(Map map) {
        TheOkRequestBuilder requestBuilder = TheOkHttp.get(map);
        return requestBuilder;
    }
    public static TheOkRequestBuilder postForm() {
        return postForm(null);
    }
    public static TheOkRequestBuilder postForm(Map map) {
        TheOkRequestBuilder requestBuilder = TheOkHttp.postForm(map);
        return requestBuilder;
    }



}
