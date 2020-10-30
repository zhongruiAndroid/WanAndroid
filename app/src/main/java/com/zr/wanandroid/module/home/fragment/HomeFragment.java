package com.zr.wanandroid.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.basecore.listener.NoDoubleClickListener;
import com.github.developtools.DensityUtils;
import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.bridge.ActBridge;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;
import com.zr.wanandroid.module.home.layout.BannerLayout;
import com.zr.wanandroid.module.home.presenter.HomeFragPresenter;

import java.util.List;

public class HomeFragment extends BaseFragment<HomeFragPresenter> {

    private RecyclerView rvHomeList;
    private BannerLayout bannerLayout;
    private HomeAdapter adapter;

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

        titleView.setAppTitle("首页");
        titleView.setAppTitleBackground(color(R.color.colorAccent));
        titleView.setAppTitleColor(color(R.color.c_white));
        titleView.setAppRightImg(R.drawable.home_search, new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View var1) {
                ActBridge.toSearchActivity(mActivity);
            }
        });
        titleView.setAppBackIcon(null);

        pcflRefresh.disableWhenHorizontalMove(true);
        rvHomeList = findViewById(R.id.rvHomeList);
        bannerLayout = new BannerLayout(getActivity());

        rvHomeList.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseItemDivider baseItemDivider = new BaseItemDivider(getContext(), DensityUtils.dp2px(20));
        baseItemDivider.setSkipEndCount(2);
        rvHomeList.addItemDecoration(baseItemDivider);
        adapter = getPresenter().initAdapter();
        adapter.addHeaderView(bannerLayout);
        rvHomeList.setAdapter(adapter);


    }

    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {
        showProgress();
        getOtherData();
        getData(1, false);
    }

    @Override
    protected void getOtherData() {
        getPresenter().getHomeBanner();
        getPresenter().getHomeTopArticleList();
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(page, isLoad);
    }

    public void setBannerData(List<HomeBannerBean> list) {
        if (bannerLayout != null) {
            bannerLayout.setData(list);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bannerLayout != null) {
            bannerLayout.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bannerLayout != null) {
            bannerLayout.stopAutoPlay();
        }
    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()) {
            case R.id.disableHome:
                break;
        }
    }

}
