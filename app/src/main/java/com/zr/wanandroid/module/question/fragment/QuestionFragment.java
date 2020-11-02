package com.zr.wanandroid.module.question.fragment;

import android.os.Bundle;
import android.view.View;

import com.zr.wanandroid.base.BaseFragment;

public class QuestionFragment extends BaseFragment {
    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public void initView() {

    }
    public static QuestionFragment newInstance() {
        Bundle args = new Bundle();
        QuestionFragment fragment = new QuestionFragment();
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
