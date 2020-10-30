package com.zr.wanandroid;

import android.app.Application;
import android.content.Context;

import com.zr.wanandroid.application.AppHelper;

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        AppHelper.initNetWork(this);
    }
    public static Context getContext() {
        return context;
    }
}
