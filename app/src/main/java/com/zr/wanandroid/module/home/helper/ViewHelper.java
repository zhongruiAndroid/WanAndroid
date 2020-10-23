package com.zr.wanandroid.module.home.helper;

import android.view.View;

public class ViewHelper {
    public static void setVisibility(View v, int visibility) {
        if (v == null) {
            return;
        }
        v.setVisibility(visibility);
    }
}
