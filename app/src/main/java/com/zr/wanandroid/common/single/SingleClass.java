package com.zr.wanandroid.common.single;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SingleClass{
    /**********************************************************/
    private static Map<Class,SingleClass> singleClassMap=new ConcurrentHashMap<>();
    public SingleClass() {
    }
    public static <T extends SingleClass> T getInstance(Class<T> clazz) {
        SingleClass singleClass = singleClassMap.get(clazz);
        if(singleClass==null){
            synchronized (SingleClass.class){
                if(singleClass==null){
                    try {
                        singleClass=clazz.newInstance();
                        singleClassMap.put(clazz,singleClass);
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
}
