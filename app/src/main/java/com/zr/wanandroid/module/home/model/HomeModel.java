package com.zr.wanandroid.module.home.model;

import com.github.theokhttp.TheOkHttp;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;
import com.zr.wanandroid.module.home.bean.SearchHotBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeModel extends SingleClass {
    public static HomeModel getInstance() {
        return getInstance(HomeModel.class);
    }


    public void getHomeBanner(final RequestListener listener) {
        TheOkHttp.startGet(NetUrl.HOME_BANNER, new HttpCallback<List<HomeBannerBean>>() {
            @Override
            public void success(List<HomeBannerBean> data, BaseResponse server, String result) {
                toSuccess(listener, data);
            }

            @Override
            public void error(String code, String errorMsg) {
                toError(listener, code, errorMsg);
            }
        });
    }

    public void getHomeArticleList(int page, final BaseRequestListener listener) {
        TheOkHttp.startGet(String.format(NetUrl.HOME_ARTICLE_LIST, (page - 1)), new HttpCallback<List<HomeArticleBean>>() {
            @Override
            public void success(List<HomeArticleBean> data, BaseResponse server, String result) {
                toSuccess(listener, data);
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
                toError(listener, code, errorMsg);
            }

            @Override
            public String getContentJson(JSONObject jsonObject) {
                return jsonObject.optJSONObject("data").optJSONArray("datas").toString();
            }
        });
    }

    public void getHomeTopArticleList(final RequestListener listener) {
        TheOkHttp.startGet(NetUrl.HOME_TOP_ARTICLE_LIST, new HttpCallback<List<HomeArticleBean>>() {
            @Override
            public void success(List<HomeArticleBean> data, BaseResponse server, String result) {
                toSuccess(listener, data);
            }

            @Override
            public void error(String code, String errorMsg) {
                toError(listener, code, errorMsg);
            }
        });
    }

    public void getHotSearchRecord(final RequestListener<List<SearchHotBean>> listener) {
        TheOkHttp.get()./*setOkHttpClient(new OkHttpClient.Builder().cache(new Cache(MyApplication.getContext().getCacheDir(),5*1014*1024)).build()).*/
                start(NetUrl.HOME_HOT_SEARCH_RECORD, new HttpCallback<List<SearchHotBean>>() {
            @Override
            public void success(List<SearchHotBean> data, BaseResponse server, String result) {
                toSuccess(listener, data);
            }

            @Override
            public void error(String code, String errorMsg) {
                toError(listener, code, errorMsg);
            }
        });
    }

    public void getSearchArticleList(int page, String searchKey, final RequestListener<List<HomeArticleBean>> listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("k", searchKey);
        TheOkHttp.postForm(map).start(String.format(NetUrl.HOME_SEARCH_ARTICLE, (page - 1)), new HttpCallback<List<HomeArticleBean>>() {
            @Override
            public void success(List<HomeArticleBean> data, BaseResponse server, String result) {
                toSuccess(listener, data);
            }

            @Override
            public void error(String code, String errorMsg) {
                toError(listener, code, errorMsg);
            }

            @Override
            public String getContentJson(JSONObject jsonObject) {
                return jsonObject.optJSONObject("data").optJSONArray("datas").toString();
            }
        });
    }
}
