package com.zr.wanandroid.utils;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.github.developtools.N;

public class ToastUtils {
    private static Toast toast;
    private static Context context;
    public static void init(Context ctx){
        context=ctx;
    }
    public static void showToast(String text){
        if(N.isTrimToEmpty(text)){
            return;
        }
        if(Looper.myLooper()==Looper.getMainLooper()){
            showText(text);
        }else{
            HandlerUtils.get().post(new Runnable() {
                @Override
                public void run() {
                    showText(text);
                }
            });
        }
    }
    private static void showText(String text){
        if(toast==null){
            toast=Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }else{
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
    public static void cancelToast(){
        if(toast!=null){
            toast.cancel();
        }
    }
}
