package com.zr.wanandroid.module.my.model;

import com.google.gson.Gson;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.HttpUtils;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;
import com.zr.wanandroid.module.my.bean.CoinBean;
import com.zr.wanandroid.module.my.bean.CoinRecordBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public void getUserCoin(RequestListener<CoinBean> listener){
        HttpUtils.get().start(NetUrl.USER_COIN, new HttpCallback<CoinBean>() {
            @Override
            public void success(CoinBean data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
    public void getUserCoinRecord(int page, BaseRequestListener<List<CoinRecordBean>,Boolean> listener){
        HttpUtils.get().start(String.format(NetUrl.USER_COIN_RECORD,page), new HttpCallback<List<CoinRecordBean>>() {
            @Override
            public void success(List<CoinRecordBean> data, BaseResponse server, String result) {
                toSuccess(listener,data);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    boolean isOver = jsonObject.optJSONObject("data").optBoolean("over");
                    if (listener != null) {
                        listener.onCustomSuccess(data,!isOver);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
            @Override
            public String getContentJson(JSONObject jsonObject) {
                JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("datas");
                if (jsonArray == null) {
                    return "";
                }
                return jsonArray.toString();
            }
        });
    }
    public void getCoinRank(int page, BaseRequestListener<List<CoinBean>,Boolean> listener){
        HttpUtils.get().start(String.format(NetUrl.All_USER_COIN_LIST,page), new HttpCallback<List<CoinBean>>() {
            @Override
            public void success(List<CoinBean> data, BaseResponse server, String result) {
                toSuccess(listener,data);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    boolean isOver = jsonObject.optJSONObject("data").optBoolean("over");
                    if (listener != null) {
                        listener.onCustomSuccess(data,!isOver);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
            @Override
            public String getContentJson(JSONObject jsonObject) {
                JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("datas");
                if (jsonArray == null) {
                    return "";
                }
                return jsonArray.toString();
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
