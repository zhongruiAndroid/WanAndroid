package com.zr.wanandroid.module.home.presenter;

import com.github.developtools.N;
import com.github.developtools.SPUtils;
import com.github.mytask.Emitter;
import com.github.mytask.Task;
import com.github.mytask.TaskPerform;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zr.wanandroid.AppXml;
import com.zr.wanandroid.BuildConfig;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.module.home.activity.SearchActivity;
import com.zr.wanandroid.module.home.bean.SearchHotBean;
import com.zr.wanandroid.module.home.model.HomeModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchPresenter extends BasePresenter<SearchActivity> {

    /*保存搜索记录到本地*/
    public void addSearchToCache(String text) {
        Task.start(new TaskPerform<List<SearchHotBean>>() {
            @Override
            public void perform(Emitter emitter) {
                String string = SPUtils.getString(AppXml.HISTORY_SEARCH_RECORD, "");

                Type type = new TypeToken<List<SearchHotBean>>() {
                }.getType();
                List<SearchHotBean> list = new Gson().fromJson(string, type);
                if (list == null) {
                    list=new ArrayList<>();
                }
                SearchHotBean hotBean = new SearchHotBean(text);
                list.remove(hotBean);
                list.add(0, hotBean);
                int limitSize=30;
                if(BuildConfig.DEBUG){
                    limitSize=30;
                }
                if(list.size()>limitSize){
                    list.remove(list.size()-1);
                }

                SPUtils.setPrefString(AppXml.HISTORY_SEARCH_RECORD, new Gson().toJson(list));
                emitter.onNext(list);
            }

            @Override
            public void onNext(List<SearchHotBean> next) {
            }
        });
    }
}
