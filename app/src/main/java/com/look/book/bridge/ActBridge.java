package com.look.book.bridge;

import android.app.Activity;
import android.content.Intent;

import com.look.book.home.activity.TestActivity;

public class ActBridge {
    public static void toTestActivity(Activity activity){
        activity.startActivity(new Intent(activity, TestActivity.class));
    }
}
