package com.look.book.bridge;

import android.app.Activity;
import android.content.Intent;

import com.look.book.test.activity.EmptyActivity;
import com.look.book.test.activity.Test2Activity;
import com.look.book.test.activity.Test3Activity;
import com.look.book.test.activity.TestActivity;

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
}
