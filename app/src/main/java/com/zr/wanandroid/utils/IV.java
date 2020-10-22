package com.zr.wanandroid.utils;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zr.wanandroid.R;

public class IV {
    public static void with(Context context, Object object, ImageView imageView){
        Glide.with(context).load(object).error(R.color.c_white).into(imageView);
    }
}
