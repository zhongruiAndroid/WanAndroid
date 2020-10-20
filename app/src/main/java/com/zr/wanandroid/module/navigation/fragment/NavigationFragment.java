package com.zr.wanandroid.module.navigation.fragment;

import android.os.Bundle;
import android.view.View;

import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.my.fragment.MyFragment;

public class NavigationFragment extends BaseFragment {
    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public void initView() {

    }
    public static NavigationFragment newInstance() {
        Bundle args = new Bundle();
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
