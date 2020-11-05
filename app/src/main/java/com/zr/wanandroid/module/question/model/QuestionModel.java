package com.zr.wanandroid.module.question.model;

import com.github.theokhttp.TheOkHttp;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.common.net.HttpUtils;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class QuestionModel extends SingleClass {
    public static QuestionModel getInstance() {
        return getInstance(QuestionModel.class);
    }

    public void getQuestionList(int page, BaseRequestListener<List<HomeArticleBean>, Boolean> listBaseRequestListener) {
        getListByUrl(page, String.format(NetUrl.QUESTION_ANSWER, page), listBaseRequestListener);
    }

    public void getSquareList(int page, BaseRequestListener<List<HomeArticleBean>, Boolean> listBaseRequestListener) {
        getListByUrl(page, String.format(NetUrl.SQUARE_LIST, (page - 1)), listBaseRequestListener);
    }

    private void getListByUrl(int page, String url, BaseRequestListener<List<HomeArticleBean>, Boolean> listBaseRequestListener) {
        HttpUtils.get().start(url, new HttpCallback<List<HomeArticleBean>>() {
            @Override
            public void success(List<HomeArticleBean> data, BaseResponse server, String result) {
                toSuccess(listBaseRequestListener, data);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    boolean isOver = jsonObject.optJSONObject("data").optBoolean("over");
                    if (listBaseRequestListener != null) {
                        listBaseRequestListener.onCustomSuccess(data, !isOver);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String code, String errorMsg) {
                toError(listBaseRequestListener, code, errorMsg);
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
}
