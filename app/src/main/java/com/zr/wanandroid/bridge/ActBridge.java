package com.zr.wanandroid.bridge;

import android.app.Activity;
import android.content.Intent;

import com.zr.wanandroid.module.web.act.WebActivity;
import com.zr.wanandroid.test.activity.EmptyActivity;
import com.zr.wanandroid.test.activity.Test2Activity;
import com.zr.wanandroid.test.activity.Test3Activity;
import com.zr.wanandroid.test.activity.TestActivity;

public class ActBridge {
    public static void toTestActivity(Activity activity){
        activity.startActivity(new Intent(activity, TestActivity.class));
    }
    public static void toTest2Activity(Activity activity){
        activity.startActivity(new Intent(activity, Test2Activity.class));
    }
    public static void toTest3Activity(Activity activity){
        activity.startActivity(new Intent(activity, Test3Activity.class));
    }
    public static void toEmptyActivity(Activity activity){
        activity.startActivity(new Intent(activity, EmptyActivity.class));
    }
    public static void toWebActivity(Activity activity,String title,String url){
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(WebActivity.INTENT_TITLE,title);
        intent.putExtra(WebActivity.INTENT_URL,url);
        activity.startActivity(intent);
    }
}
