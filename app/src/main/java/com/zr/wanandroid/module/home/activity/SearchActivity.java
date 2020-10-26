package com.zr.wanandroid.module.home.activity;

import android.view.View;

import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;

public class SearchActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.search_act;
    }

    @Override
    public void initView() {
        titleView.setAppRightTitle("搜索");
        titleView.setAppRightTitleColor(color(R.color.c_white));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
