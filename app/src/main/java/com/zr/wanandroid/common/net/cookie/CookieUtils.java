package com.zr.wanandroid.common.net.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.github.developtools.N;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zr.wanandroid.MyApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class CookieUtils {
    /**********************************************************/
    private static CookieUtils singleObj;

    private CookieUtils() {
    }

    public static CookieUtils get() {
        if (singleObj == null) {
            synchronized (CookieUtils.class) {
                if (singleObj == null) {
                    singleObj = new CookieUtils();
                }
            }
        }
        return singleObj;
    }

    public Map<String, Cookie> getMap() {
        if(map==null){
            map= new ConcurrentHashMap<>();
        }
        return map;
    }
    /**********************************************************/
    public Map<String, Cookie> map;
    public void saveFromResponse(List<Cookie> cookies) {
        for (Cookie cache : cookies) {
            String cookieKey = getCookieKey(cache);
            if(N.trimToEmptyNull(cookieKey)){
                continue;
            }
            getMap().remove(cookieKey);
            getMap().put(cookieKey, cache);
        }
        if(map!=null&&map.size()>0){
            saveCookieToLocal(map);
        }
    }

    private String getCookieKey(Cookie cookie) {
        if (cookie == null) {
            return "";
        }
        String domain = cookie.domain();
        String name = cookie.name();
        boolean hostOnly = cookie.hostOnly();
        boolean secure = cookie.secure();
        String path = cookie.path();
        return domain + name + hostOnly + secure + path;
    }

    public List<Cookie> loadForRequest(HttpUrl url) {
        if (map == null||map.size()<=0) {
            map=getCookieFromLocal();
        }
        if (map == null||map.size()<=0) {
            return Collections.emptyList();
        }
        List<Cookie> cookieCaches = new ArrayList<>();

        Iterator<Map.Entry<String, Cookie>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Cookie> next = iterator.next();
            Cookie value = next.getValue();
            if (value == null) {
                continue;
            }
            if (isCookieExpired(value)) {
                iterator.remove();
            } else if (value.matches(url)) {
                cookieCaches.add(value);
            }
        }
        return cookieCaches;
    }
    public void clearSession() {
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("cookieSharedPreferences", Context.MODE_PRIVATE);
        preferences.edit().clear();
        map.clear();

        WebViewCookieUtils.get().clearCookie();
    }
    private boolean isCookieExpired(Cookie cookie) {
        if (cookie == null) {
            return true;
        }
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    private void saveCookieToLocal(Map<String, Cookie> map) {
        String json = new Gson().toJson(map);
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("cookieSharedPreferences", Context.MODE_PRIVATE);
        preferences.edit().putString("cookieKey",json).commit();
    }
    private Map<String, Cookie> getCookieFromLocal() {
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("cookieSharedPreferences", Context.MODE_PRIVATE);
        String cookieKey = preferences.getString("cookieKey", "");
        if(TextUtils.isEmpty(cookieKey)){
            return null;
        }
        Map map = new Gson().fromJson(cookieKey,new TypeToken<Map<String, Cookie>>(){}.getType());
        return map;
    }
}
