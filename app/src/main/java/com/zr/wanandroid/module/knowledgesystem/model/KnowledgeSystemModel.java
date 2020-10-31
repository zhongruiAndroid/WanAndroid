package com.zr.wanandroid.module.knowledgesystem.model;

import com.github.theokhttp.TheOkHttp;
import com.github.theokhttp.TheOkHttpCallback;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;

import java.util.List;

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

}
