package com.zr.wanandroid.module.home.helper;

import android.view.View;
import android.view.animation.AlphaAnimation;

public class ViewHelper {
    public static void setVisibility(View v, int visibility) {
        setVisibility(v,visibility,0);
    }
    public static void setVisibility(View v, int visibility,int duration) {
        if (v == null) {
            return;
        }
        if(duration>0){
            AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setDuration(duration);
            v.startAnimation(alphaAnimation);
        }
        v.setVisibility(visibility);
    }
}
