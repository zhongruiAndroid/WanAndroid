package com.zr.wanandroid.module.home.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.dividerline.BaseItemDivider;
import com.github.theokhttp.NetworkUtils;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.home.presenter.HomeFragPresenter;

public class HomeFragment extends BaseFragment<HomeFragPresenter>  {

    private RecyclerView rvHomeList;
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
        rvHomeList =   findViewById(R.id.rvHomeList);

        rvHomeList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHomeList.addItemDecoration(new BaseItemDivider(getContext(),2));
        rvHomeList.setAdapter(getPresenter().initAdapter());


    }

    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {
        showProgress();
        getData(1,false);
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
