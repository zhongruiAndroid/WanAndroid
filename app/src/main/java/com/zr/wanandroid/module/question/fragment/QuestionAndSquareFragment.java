package com.zr.wanandroid.module.question.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.common.adapter.FragmentListAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionAndSquareFragment extends BaseFragment {
    private TabLayout tlQuestionAndSquareTab;
    private ViewPager vpQuestionAndSquare;
    private FragmentListAdapter adapter;
    private List<Fragment> list;
    @Override
    public int getContentView() {
        return R.layout.question_and_square_frag;
    }

    @Override
    public void initView() {
        tlQuestionAndSquareTab = (TabLayout) findViewById(R.id.tlQuestionAndSquareTab);
        vpQuestionAndSquare = (ViewPager) findViewById(R.id.vpQuestionAndSquare);
    }

    public static QuestionAndSquareFragment newInstance() {
        Bundle args = new Bundle();
        QuestionAndSquareFragment fragment = new QuestionAndSquareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {
        adapter=new FragmentListAdapter(getChildFragmentManager());
        list=new ArrayList<>();
        list.add(QuestionFragment.newInstance());
        list.add(SquareFragment.newInstance());

        List<String>titleList=new ArrayList<>();
        titleList.add("每日一问");
        titleList.add("话题广场");

        adapter.setList(list);
        adapter.setTitleList(titleList);

        vpQuestionAndSquare.setAdapter(adapter);
        tlQuestionAndSquareTab.setupWithViewPager(vpQuestionAndSquare);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
