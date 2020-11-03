package com.zr.wanandroid.module.officialaccount.model;

import com.github.theokhttp.TheOkHttp;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.officialaccount.bean.OfficialAccountBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class OfficialAccountModel extends SingleClass {
    public static OfficialAccountModel getInstance() {
        return getInstance(OfficialAccountModel.class);
    }
    public void getOfficialAccountAuthorList(RequestListener<List<OfficialAccountBean>> listener){
        TheOkHttp.startGet(NetUrl.OFFICIAL_ACCOUNT_LIST, new HttpCallback<List<OfficialAccountBean>>() {
            @Override
            public void success(List<OfficialAccountBean> data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
    public void getOfficialArticleListByAuthor(int page, String authorId, BaseRequestListener<List<HomeArticleBean>,Boolean> listener){
        /*此处page从1开始，有些接口从0开始*/
        TheOkHttp.startGet(String.format(NetUrl.OFFICIAL_ACCOUNT_ARTICLE_LIST,authorId,(page)), new HttpCallback<List<HomeArticleBean>>() {
            @Override
            public void success(List<HomeArticleBean> data, BaseResponse server, String result) {
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
            public String getContentJson(JSONObject jsonObject) {
                JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("datas");
                if(jsonArray==null){
                    return "";
                }
                return jsonArray.toString();
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
}
