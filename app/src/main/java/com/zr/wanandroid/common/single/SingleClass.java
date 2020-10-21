package com.zr.wanandroid.common.single;

import com.zr.wanandroid.common.listener.RequestListener;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SingleClass {
    /**********************************************************/
    private static WeakReference<ConcurrentHashMap<Class, SingleClass>> weakReference = new WeakReference<>(new ConcurrentHashMap<Class, SingleClass>());

    public SingleClass() {
    }

    public static ConcurrentHashMap<Class, SingleClass> getMap() {
        ConcurrentHashMap<Class, SingleClass> classSingleClassConcurrentHashMap = weakReference.get();
        if (classSingleClassConcurrentHashMap == null) {
            classSingleClassConcurrentHashMap = new ConcurrentHashMap<Class, SingleClass>();
        }
        return classSingleClassConcurrentHashMap;
    }

    public static <T extends SingleClass> T getInstance(Class<T> clazz) {
        SingleClass singleClass = getMap().get(clazz);
        if (singleClass == null) {
            synchronized (SingleClass.class) {
                if (singleClass == null) {
                    try {
                        singleClass = clazz.newInstance();
                        getMap().put(clazz, singleClass);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (T) singleClass;
    }

    public <T>void success(RequestListener<T> listener,T obj){
        if (listener != null) {
            listener.onSuccess(obj);
        }
    }
    public <T>void error(RequestListener<T> listener, String code, String errorMsg){
        if (listener != null) {
            listener.onError(code,errorMsg);
        }
    }
}
