package com.zr.wanandroid.module.officialaccount.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.navigation.fragment.NavigationFragment;

public class OfficialAccountFragment extends BaseFragment {
    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public void initView() {

    }
    public static OfficialAccountFragment newInstance() {
        Bundle args = new Bundle();
        OfficialAccountFragment fragment = new OfficialAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {
        Log.i("=====","=====initData");
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
