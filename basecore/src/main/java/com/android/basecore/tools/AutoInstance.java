package com.android.basecore.tools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class AutoInstance {
    public static <Presenter>  Presenter autoCreatePresenter(Class clazz) {
        Presenter presenter;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            //参数化类型
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //返回表示此类型实际类型参数的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            try {
                presenter = ((Class<Presenter>) actualTypeArguments[0]).newInstance();
            } catch (Exception e) {
                presenter = null;
                e.printStackTrace();
            }
        } else {
            presenter = null;
        }
        return presenter;
    }
}
