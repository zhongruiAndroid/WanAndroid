package com.zr.wanandroid.module.home.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.dividerline.BaseItemDivider;
import com.github.theokhttp.NetworkUtils;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;
import com.zr.wanandroid.module.home.layout.BannerLayout;
import com.zr.wanandroid.module.home.presenter.HomeFragPresenter;

import java.util.List;

public class HomeFragment extends BaseFragment<HomeFragPresenter>  {

    private RecyclerView rvHomeList;
    private BannerLayout
            bannerLayout;
    @Override
    public int getContentView() {
        return R.layout.home_frag;
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        pcflRefresh.disableWhenHorizontalMove(true);
        rvHomeList =   findViewById(R.id.rvHomeList);
        bannerLayout =   findViewById(R.id.bannerLayout);

        rvHomeList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHomeList.addItemDecoration(new BaseItemDivider(getContext(),2));
        rvHomeList.setAdapter(getPresenter().initAdapter());


    }

    @Override
    public void initViewAfter() {

    }
    public void setBannerData(List<HomeBannerBean> list){
        if (bannerLayout != null) {
            bannerLayout.setData(list);
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    public void initData() {
        showProgress();
        getOtherData();
        getData(1,false);
    }
    @Override
    protected void getOtherData() {
        getPresenter().getHomeBanner();
        getPresenter().getHomeTopArticleList();
    }
    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(page,isLoad);
    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.disableHome:
            break;
        }
    }

}
