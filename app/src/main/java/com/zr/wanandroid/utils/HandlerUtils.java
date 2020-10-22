package com.zr.wanandroid.utils;

import android.os.Handler;
import android.os.Looper;

public class HandlerUtils {
    /**********************************************************/
    private static Handler singleObj;
    private HandlerUtils() {
    }
    public static Handler get(){
        if(singleObj==null){
            synchronized (HandlerUtils.class){
                if(singleObj==null){
                    singleObj=new Handler(Looper.getMainLooper());
                }
            }
        }
        return singleObj;
    }
    /**********************************************************/

}
