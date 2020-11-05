package com.zr.wanandroid.common.net;

import com.github.developtools.N;
import com.github.developtools.SPUtils;
import com.github.theokhttp.TheOkHttp;
import com.github.theokhttp.TheOkRequestBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zr.wanandroid.AppXml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    public static final String SetCookie="Set-Cookie";
    private static List<String>headerList;
    public static TheOkRequestBuilder get() {
        return get(null);
    }
    public static TheOkRequestBuilder get(Map map) {
        TheOkRequestBuilder requestBuilder = TheOkHttp.get(map);
        addCookie(requestBuilder);
        return requestBuilder;
    }
    public static TheOkRequestBuilder postForm() {
        return postForm(null);
    }
    public static TheOkRequestBuilder postForm(Map map) {
        TheOkRequestBuilder requestBuilder = TheOkHttp.postForm(map);
        addCookie(requestBuilder);
        return requestBuilder;
    }
    private static void addCookie(TheOkRequestBuilder requestBuilder){
        if(N.isEmpty(headerList)){
            headerList=new ArrayList<>();
            String string = SPUtils.getString(AppXml.COOKIE_KEY, "");
            if(!N.trimToEmptyNull(string)){
                headerList=new Gson().fromJson(string,new TypeToken<List<String>>(){}.getType());
            }
        }
        if(N.isEmpty(headerList)){
            return;
        }
        requestBuilder.removeHeader(SetCookie);
        for (String str:headerList){
            requestBuilder.addHeader(SetCookie,str);
        }
    }
}
