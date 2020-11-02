package com.zr.wanandroid.application;

import android.app.Application;

import com.github.developtools.SPUtils;
import com.github.theokhttp.NetworkUtils;
import com.github.theokhttp.TheOkClientManager;
import com.github.theokhttp.TheOkHttp;
import com.zr.wanandroid.BuildConfig;
import com.zr.wanandroid.utils.ToastUtils;

import java.io.File;

import okhttp3.Cache;

public class AppHelper {
    public static void initNetWork(Application application){
        TheOkHttp.init().cache(new Cache(new File(application.getCacheDir(),"http"),5*1024*1024)).complete();
        TheOkHttp.setDebug(BuildConfig.DEBUG);
        NetworkUtils.init(application);
        ToastUtils.init(application);
        SPUtils.init(application);
    }
}
