package com.zr.wanandroid.module.home.model;

import com.github.theokhttp.TheOkHttp;
import com.github.theokhttp.TheOkHttpCallback;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.single.SingleClass;

import java.io.InputStream;
import java.io.Reader;

public class HomeModel extends SingleClass {
    public static HomeModel getInstance() {
        return getInstance(HomeModel.class);
    }
    public void getHomeTopArticleList(final RequestListener listener){
        TheOkHttp.startGet(NetUrl.HOME_BANNER, new TheOkHttpCallback<byte[]>() {
            @Override
            public void response(byte[] response) {
                success(listener,response);
            }
            @Override
            public void failure(Exception e) {
                error(listener,"",e.getMessage());
            }
        });
    }
}
