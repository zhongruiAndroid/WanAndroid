package com.zr.wanandroid.module.home.model;

import com.github.theokhttp.TheOkHttp;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;

import org.json.JSONObject;

import java.util.List;

public class HomeModel extends SingleClass {
    public static HomeModel getInstance() {
        return getInstance(HomeModel.class);
    }


    public void getHomeBanner(final RequestListener listener){
        TheOkHttp.startGet(NetUrl.HOME_BANNER, new HttpCallback<List<HomeBannerBean>>() {
            @Override
            public void success(List<HomeBannerBean> data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
    public void getHomeArticleList(int page,final RequestListener listener){
        TheOkHttp.startGet(NetUrl.HOME_ARTICLE_LIST+(page-1)+NetUrl.HOME_ARTICLE_LIST_END, new HttpCallback<List<HomeArticleBean>>() {
            @Override
            public void success(List<HomeArticleBean> data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }

            @Override
            public String getContentJson(JSONObject jsonObject) {
                return jsonObject.optJSONObject("data").optJSONArray("datas").toString();
            }
        });
    }
    public void getHomeTopArticleList(final RequestListener listener){
        TheOkHttp.startGet(NetUrl.HOME_TOP_ARTICLE_LIST, new HttpCallback<List<HomeArticleBean>>() {
            @Override
            public void success(List<HomeArticleBean> data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
}
