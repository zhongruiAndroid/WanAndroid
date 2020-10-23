package com.zr.wanandroid.module.home.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.developtools.DensityUtils;
import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;
import com.zr.wanandroid.module.home.layout.BannerLayout;
import com.zr.wanandroid.module.home.presenter.HomeFragPresenter;

import java.util.List;

public class HomeFragment extends BaseFragment<HomeFragPresenter> {

    private RecyclerView rvHomeList;
    private BannerLayout bannerLayout;

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
        rvHomeList = findViewById(R.id.rvHomeList);
        bannerLayout = new BannerLayout(getActivity());

        rvHomeList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHomeList.addItemDecoration(new BaseItemDivider(getContext(), DensityUtils.dp2px(15)));
        HomeAdapter adapter = getPresenter().initAdapter();
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
