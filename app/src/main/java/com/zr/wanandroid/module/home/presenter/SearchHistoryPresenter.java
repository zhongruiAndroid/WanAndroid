package com.zr.wanandroid.module.home.presenter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.github.developtools.N;
import com.github.developtools.SPUtils;
import com.github.mydialog.TheDialog;
import com.github.mytask.Emitter;
import com.github.mytask.Task;
import com.github.mytask.TaskPerform;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zr.wanandroid.AppXml;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.home.activity.SearchActivity;
import com.zr.wanandroid.module.home.bean.SearchHotBean;
import com.zr.wanandroid.module.home.fragment.SearchHistoryFragment;
import com.zr.wanandroid.module.home.model.HomeModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchHistoryPresenter extends BasePresenter<SearchHistoryFragment> {
    public void getHotSearch() {
        HomeModel.getInstance().getHotSearchRecord(new RequestListener<List<SearchHotBean>>() {
            @Override
            public void onSuccess(List<SearchHotBean> obj) {
                if(N.isEmpty(obj)){
                    return;
                }
                getView().setHotSearchRecord(obj);
            }
            @Override
            public void onError(String code, String errorMsg) {

            }
        });
    }

    public void getHistorySearch() {
        Task.start(new TaskPerform<List<SearchHotBean>>() {
            @Override
            public void perform(Emitter emitter) {
                String string = SPUtils.getString(AppXml.HISTORY_SEARCH_RECORD, "");
                if(N.trimToEmptyNull(string)){
                    emitter.onNext(new ArrayList<SearchHotBean>());
                    return;
                }
                Type type = new TypeToken<List<SearchHotBean>>(){}.getType();
                List<SearchHotBean> list = new Gson().fromJson(string, type);
                emitter.onNext(list);
            }
            @Override
            public void onNext(List<SearchHotBean> next) {
                getView().setHistorySearchRecord(next);
            }
        });
    }

    public void clearHistorySearchRecord() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getView().getContext());
        builder.setTitle("提示");
        builder.setMessage("是否确认清除历史搜索记录?");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getView().hiddenHistorySearch();
                SPUtils.removeKey(AppXml.HISTORY_SEARCH_RECORD);
            }
        });
        builder.show();
    }
}
