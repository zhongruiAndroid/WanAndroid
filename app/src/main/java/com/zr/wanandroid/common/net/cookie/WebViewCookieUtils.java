package com.zr.wanandroid.common.net.cookie;

import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.github.developtools.WebViewUtils;
import com.zr.wanandroid.MyApplication;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class WebViewCookieUtils {
    /**********************************************************/
    private static WebViewCookieUtils singleObj;
    private WebViewCookieUtils() {
    }
    public static WebViewCookieUtils get(){
        if(singleObj==null){
            synchronized (WebViewUtils.class){
                if(singleObj==null){
                    singleObj=new WebViewCookieUtils();
                }
            }
        }
        return singleObj;
    }
    /**********************************************************/
    public void setCookie(String url){
        if(TextUtils.isEmpty(url)){
            return;
        }
        List<Cookie> list = CookieUtils.get().loadForRequest(HttpUrl.get(url));
        if(list==null||list.size()<=0){
            return;
        }
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            instance.removeAllCookies(null);
        }else{
            instance.removeExpiredCookie();
            instance.removeSessionCookie();
        }

        for (Cookie cookie : list) {
            instance.setCookie(url,cookie.name()+"="+cookie.value());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            instance.flush();
        }else{
            CookieSyncManager.createInstance(MyApplication.getContext());
            CookieSyncManager.getInstance().sync();
        }

    }
    public void clearCookie(){
        CookieManager instance = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            instance.removeAllCookies(null);
        }else{
            instance.removeExpiredCookie();
            instance.removeSessionCookie();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            instance.flush();
        }else{
            CookieSyncManager.createInstance(MyApplication.getContext());
            CookieSyncManager.getInstance().sync();
        }
    }
}
