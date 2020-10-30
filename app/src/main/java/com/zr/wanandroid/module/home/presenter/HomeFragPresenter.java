package com.zr.wanandroid.module.home.presenter;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;
import com.zr.wanandroid.module.home.fragment.HomeFragment;
import com.zr.wanandroid.module.home.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragPresenter extends BasePresenter<HomeFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private HomeAdapter adapter;
    private List<HomeArticleBean> topArticleList;
    public HomeAdapter initAdapter() {
        topArticleList=new ArrayList<>();
        adapter=new HomeAdapter(getView().getActivity());
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
                getView().showError();
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
    public void loadMore(LoadListener loadListener) {
        getData(pageNum,true);
    }
}
