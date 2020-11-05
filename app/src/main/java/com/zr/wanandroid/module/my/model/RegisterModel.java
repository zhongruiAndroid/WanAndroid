package com.zr.wanandroid.module.my.model;

import android.util.Log;

import com.github.developtools.SPUtils;
import com.github.theokhttp.TheOkHttp;
import com.google.gson.Gson;
import com.zr.wanandroid.AppXml;
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

public class RegisterModel extends SingleClass {
    public static RegisterModel getInstance() {
        return getInstance(RegisterModel.class);
    }
    public void register(String userName, String pwd,String rePwd ,RequestListener listener){
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
    public void login(String userName, String pwd, RequestListener listener){
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",userName);
        map.put("password",pwd);
        HttpUtils.postForm(map).start(NetUrl.USER_LOGIN, new HttpCallback<String>() {
            public String cookie="";
            @Override
            public void success(String data, BaseResponse server, String result) {
                toSuccess(listener,data);
                SPUtils.setPrefString(AppXml.COOKIE_KEY, cookie);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                super.onResponse(call, response);
                List<String> headers = response.headers("Set-Cookie");
                cookie=new Gson().toJson(headers);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });

    }
}
