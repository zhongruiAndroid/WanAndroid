package com.zr.wanandroid.application;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.github.developtools.SPUtils;
import com.github.load.Loading;
import com.github.theokhttp.NetworkUtils;
import com.github.theokhttp.TheOkHttp;
import com.zr.wanandroid.BuildConfig;
import com.zr.wanandroid.R;
import com.zr.wanandroid.common.net.cookie.CookieUtils;
import com.zr.wanandroid.utils.ToastUtils;

import java.io.File;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class AppHelper {
    public static void initNetWork(Application application){
        OkHttpClient.Builder client=new OkHttpClient.Builder();
        client.cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                CookieUtils.get().saveFromResponse(cookies);
            }
            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return CookieUtils.get().loadForRequest(url);
            }
        });
        client.cache(new Cache(new File(application.getCacheDir(),"http"),5*1024*1024));
        TheOkHttp.init(client.build());

        TheOkHttp.setDebug(BuildConfig.DEBUG);
        NetworkUtils.init(application);
        ToastUtils.init(application);
        SPUtils.init(application);
        Loading.get().setLoadViewColor(ContextCompat.getColor(application, R.color.colorAccent));
    }
}
