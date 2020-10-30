package com.zr.wanandroid.application;

import android.app.Application;

import com.github.developtools.SPUtils;
import com.github.theokhttp.NetworkUtils;
import com.github.theokhttp.TheOkClientManager;
import com.github.theokhttp.TheOkHttp;
import com.zr.wanandroid.BuildConfig;
import com.zr.wanandroid.utils.ToastUtils;

public class AppHelper {
    public static void initNetWork(Application application){
        TheOkHttp.init();
        TheOkHttp.setDebug(BuildConfig.DEBUG);
        NetworkUtils.init(application);
        ToastUtils.init(application);
        SPUtils.init(application);
    }
}
