package com.zr.wanandroid.module.my.fragment;

import android.os.Bundle;
import android.view.View;

import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.knowledgesystem.fragment.KnowledgeSystemFragment;

public class MyFragment extends BaseFragment {
    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public void initView() {

    }
    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
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
