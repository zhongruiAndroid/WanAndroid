package com.zr.wanandroid;

import android.app.Application;

import com.zr.wanandroid.application.AppHelper;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppHelper.initNetWork(this);
    }
}
