package com.zr.wanandroid.application;

import com.github.theokhttp.TheOkClientManager;
import com.github.theokhttp.TheOkHttp;

public class AppHelper {
    public static void initNetWork(){
        TheOkHttp.init();
    }
}
