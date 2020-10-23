package com.zr.wanandroid.module.home.presenter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.Touch;
import android.util.Log;

import com.android.basecore.widget.adapter.CustomViewHolder;
import com.android.basecore.widget.adapter.LoadInter;
import com.android.basecore.widget.adapter.LoadMoreAdapter;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.home.activity.HomeActivity;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;
import com.zr.wanandroid.module.home.fragment.HomeFragment;
import com.zr.wanandroid.module.home.layout.BannerLayout;
import com.zr.wanandroid.module.home.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragPresenter extends BasePresenter<HomeFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private HomeAdapter adapter;
    private List<HomeArticleBean> topArticleList;
    public HomeAdapter initAdapter() {
        topArticleList=new ArrayList<>();
        adapter=new HomeAdapter();
        adapter.setList(topArticleList);
        adapter.setOnLoadMoreListener(this);

        return adapter;
    }

    @Override
    public void onResumeForFragment() {
        super.onResumeForFragment();
    }
    @Override
    public void onStopForFragment() {
        super.onStopForFragment();
    }
    /*获取首页banner*/
    public void getHomeBanner() {
        HomeModel.getInstance().getHomeBanner(new RequestListener<List<HomeBannerBean>>() {
            @Override
            public void onSuccess(List<HomeBannerBean> obj) {
                getView().setBannerData( obj);
            }
            @Override
            public void onError(String code, String errorMsg) {
            }
        });
    }
    /*获取首页置顶文章*/
    public void getHomeTopArticleList() {
        HomeModel.getInstance().getHomeTopArticleList(new RequestListener<List<HomeArticleBean>>() {
            @Override
            public void onSuccess(List<HomeArticleBean> obj) {
                topArticleList.clear();
                topArticleList.addAll(obj);
                adapter.setList(topArticleList);
                adapter.setTopListSize(obj.size());
            }
            @Override
            public void onError(String code, String errorMsg) {
            }
        });
    }
    /*获取首页普通文章列表*/
    public void getData(int page,boolean isLoad) {
        HomeModel.getInstance().getHomeArticleList(page,new RequestListener<List<HomeArticleBean>>() {
            @Override
            public void onSuccess(List<HomeArticleBean> obj) {
                loadResult(isLoad);
                adapter.addList(obj,true);
                if(!isLoad){
                    getView().showContent();
                }
            }
            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
                loadError(adapter);
                if(!isLoad){
                    getView().showError();
                }
            }
        });
    }

    @Override
    public void loadMore(LoadInter loadInter) {
        getData(pageNum,true);
    }
}
