package com.zr.wanandroid.module.knowledgesystem.fragment;

import android.os.Bundle;
import android.view.View;

import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.home.fragment.HomeFragment;

public class KnowledgeSystemFragment extends BaseFragment {
    @Override
    public int getContentView() {
        return 0;
    }
    public static KnowledgeSystemFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeSystemFragment fragment = new KnowledgeSystemFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {

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
