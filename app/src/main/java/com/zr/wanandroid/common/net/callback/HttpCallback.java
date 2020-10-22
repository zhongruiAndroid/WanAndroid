package com.zr.wanandroid.common.net.callback;

import android.text.TextUtils;

import com.github.developtools.N;
import com.github.theokhttp.TheOkHttpCallback;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.zr.wanandroid.common.net.NetCode;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.utils.HandlerUtils;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallback <T> extends TheOkHttpCallback<String> {
    public static final String errorCode="errorCode";
    public static final String errorMsg="errorMsg";
    public abstract void success(T data, BaseResponse server, String result);
    public abstract void error(String code, String errorMsg);
    private void successOnUi(T data, BaseResponse server, String result){
        HandlerUtils.get().post(new Runnable() {
            @Override
            public void run() {
                success((T) data, server, result);
            }
        });
    }
    private void errorOnUi(String code, String errorMsg){
        HandlerUtils.get().post(new Runnable() {
            @Override
            public void run() {
                error(code,errorMsg);
            }
        });
    }
    @Override
    public void response(String result) {
        if (N.isEmpty(result)) {
            errorOnUi("", "数据为空");
            return;
        }
        try {
            result = result.replace("[]", "null");
            JSONObject jsonObject = new JSONObject(result);
            boolean successResult = isSuccess(jsonObject);
            if (successResult) {
                Gson gson = new Gson();
                BaseResponse baseServer = gson.fromJson(result, BaseResponse.class);
                if (baseServer == null) {
                    errorOnUi("", "数据错误");
                } else {
                    String contentJson = getContentJson(jsonObject);
                    Type type = getSuperclassTypeParameter(getClass());
                    if (type == String.class) {
                        successOnUi((T) contentJson, baseServer, result);
                    } else {
                        successOnUi((T) gson.fromJson(contentJson, type), baseServer, result);
                    }
                }
            } else {
                String code = jsonObject.optString(errorCode);
                String msg = jsonObject.optString(errorMsg);
                if (needAutoJumpLogin() && TextUtils.equals(code, NetCode.ERR_CODE_TOKEN)) {

                }
                errorOnUi(code, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorOnUi("", "解析错误");
        }
    }
    @Override
    public void failure(Exception e) {
        errorOnUi("",e.getMessage());
    }
    public boolean isSuccess(JSONObject jsonObject) {
        String code = jsonObject.optString(errorCode);
        if (TextUtils.equals("0", code)) {
            return true;
        }
        return false;
    }
    public boolean needAutoJumpLogin() {
        return true;
    }

    public String getContentJson(JSONObject jsonObject) {
        return jsonObject.optString("data");
    }

    public Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }
}
