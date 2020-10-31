package com.zr.wanandroid.module.knowledgesystem.model;

import com.github.theokhttp.TheOkHttp;
import com.github.theokhttp.TheOkHttpCallback;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeSystemModel extends SingleClass {

    public static KnowledgeSystemModel getInstance() {
        return getInstance(KnowledgeSystemModel.class);
    }

    public void getKnowledgeSystemList(RequestListener<List<KnowledgeSystemBean>> listener){
        TheOkHttp.startGet(NetUrl.KNOWLEDGE_SYSTEM, new HttpCallback<List<KnowledgeSystemBean>>() {
            @Override
            public void success(List<KnowledgeSystemBean> data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
    public void getKnowledgeSystemArticleList(int page, String cid, BaseRequestListener<List<HomeArticleBean>,Boolean> listener){
        Map<String,String> map=new HashMap<String,String>();
        map.put("cid",cid);
        TheOkHttp.get(map).start(String.format(NetUrl.KNOWLEDGE_SYSTEM_ARTICLE,(page-1)), new HttpCallback<List<HomeArticleBean>>() {
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
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
            @Override
            public String getContentJson(JSONObject jsonObject) {
                JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("datas");
                if(jsonArray==null){
                    return "";
                }
                return jsonArray.toString();
            }
        });
    }

}
