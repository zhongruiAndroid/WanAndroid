package com.zr.wanandroid.module.my.model;

import com.google.gson.Gson;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.HttpUtils;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class UserModel extends SingleClass {
    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }
    public void register(String userName, String pwd,String rePwd ,RequestListener<String> listener){
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",userName);
        map.put("password",pwd);
        map.put("repassword",rePwd);
        HttpUtils.postForm(map).start(NetUrl.USER_REGISTER, new HttpCallback<String>() {
            @Override
            public void success(String data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
    public void login(String userName, String pwd, RequestListener<String> listener){
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",userName);
        map.put("password",pwd);
        HttpUtils.postForm(map).start(NetUrl.USER_LOGIN, new HttpCallback<String>() {
            public String cookie="";
            @Override
            public void success(String data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<String> headers = response.headers("Set-Cookie");
                cookie=new Gson().toJson(headers);
                super.onResponse(call, response);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
    public void getUserCoin(RequestListener<String> listener){
        HttpUtils.get().start(NetUrl.USER_COIN, new HttpCallback<String>() {
            @Override
            public void success(String data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
    public void loginOut(RequestListener<String> listener){
        HttpUtils.get().start(NetUrl.LOGOUT, new HttpCallback<String>() {
            @Override
            public void success(String data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
}
