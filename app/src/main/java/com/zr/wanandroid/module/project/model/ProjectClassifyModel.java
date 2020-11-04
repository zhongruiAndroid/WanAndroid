package com.zr.wanandroid.module.project.model;

import com.github.theokhttp.TheOkHttp;
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

public class ProjectClassifyModel extends SingleClass {
    public static ProjectClassifyModel getInstance() {
        return getInstance(ProjectClassifyModel.class);
    }

    public void getProjectClassify(RequestListener<List<KnowledgeSystemBean>> listener) {
        TheOkHttp.startGet(NetUrl.PROJECT_CLASSIFY, new HttpCallback<List<KnowledgeSystemBean>>() {
            @Override
            public void success(List<KnowledgeSystemBean> data, BaseResponse server, String result) {
                toSuccess(listener, data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener, code, errorMsg);
            }
        });

    }

    public void getProjectList(int page, String id, BaseRequestListener<List<HomeArticleBean>, Boolean> listener) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("cid",id);
        TheOkHttp.get(map).start(String.format(NetUrl.PROJECT_LIST,page), new HttpCallback<List<HomeArticleBean>>() {
            @Override
            public void success(List<HomeArticleBean> data, BaseResponse server, String result) {
                toSuccess(listener, data);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    boolean isOver = jsonObject.optJSONObject("data").optBoolean("over");
                    if (listener != null) {
                        listener.onCustomSuccess(data, !isOver);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public String getContentJson(JSONObject jsonObject) {
                JSONArray jsonArray = jsonObject.optJSONObject("data").optJSONArray("datas");
                if (jsonArray == null) {
                    return "";
                }
                return jsonArray.toString();
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener, code, errorMsg);
            }
        });

    }
}
