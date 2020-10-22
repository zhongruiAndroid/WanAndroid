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
import com.zr.wanandroid.module.home.bean.HomeBannerBean;
import com.zr.wanandroid.module.home.fragment.HomeFragment;
import com.zr.wanandroid.module.home.layout.BannerLayout;
import com.zr.wanandroid.module.home.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragPresenter extends BasePresenter<HomeFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private HomeAdapter adapter;
    private BannerLayout bannerLayout;
    public RecyclerView.Adapter initAdapter() {
        adapter=new HomeAdapter();
        adapter.setList(new ArrayList());
        adapter.setOnLoadMoreListener(this);

        bannerLayout=new BannerLayout(getView().getActivity());
        adapter.addHeaderView(bannerLayout);
        return adapter;
    }

    @Override
    public void onResumeForFragment() {
        super.onResumeForFragment();
        if (bannerLayout != null) {
            bannerLayout.startAutoPlay();
        }
    }
    @Override
    public void onStopForFragment() {
        super.onStopForFragment();
        if (bannerLayout != null) {
            bannerLayout.stopAutoPlay();
        }
    }
    public void getHomeBanner() {
        HomeModel.getInstance().getHomeBanner(new RequestListener<List<HomeBannerBean>>() {
            @Override
            public void onSuccess(List<HomeBannerBean> obj) {
                if (bannerLayout != null) {
                    bannerLayout.setData(obj);
                }
            }
            @Override
            public void onError(String code, String errorMsg) {
            }
        });
    }
    public void getHomeTopArticleList() {
        HomeModel.getInstance().getHomeTopArticleList(new RequestListener<List<HomeBannerBean>>() {
            @Override
            public void onSuccess(List<HomeBannerBean> obj) {
            }
            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
            }
        });
    }
    public void getData(int page,boolean isLoad) {
        HomeModel.getInstance().getHomeArticleList(page,new RequestListener<List<HomeBannerBean>>() {
            @Override
            public void onSuccess(List<HomeBannerBean> obj) {
                adapter.addList(obj,true);
                loadResult(isLoad);
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
