package com.zr.wanandroid.application;

import android.app.Application;

import com.github.theokhttp.NetworkUtils;
import com.github.theokhttp.TheOkClientManager;
import com.github.theokhttp.TheOkHttp;

public class AppHelper {
    public static void initNetWork(Application application){
        TheOkHttp.init();
        NetworkUtils.init(application);
    }
}
