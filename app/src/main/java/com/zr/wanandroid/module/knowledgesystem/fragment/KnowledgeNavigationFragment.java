package com.zr.wanandroid.module.knowledgesystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.fastshape.MyTextView;
import com.github.fastshape.viewhelper.SecondHelper;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.common.adapter.FragmentListAdapter;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeNavigationFragment extends BaseFragment {
    private MyTextView tvKnowledgeTabSystem;
    private MyTextView tvKnowledgeTabNavigation;
    private ViewPager vpKnowledgeNavigation;

    private FragmentListAdapter adapter;
    @Override
    public int getContentView() {
        return R.layout.knowledge_navigation_frag;
    }

    public static KnowledgeNavigationFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeNavigationFragment fragment = new KnowledgeNavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {

        tvKnowledgeTabSystem = (MyTextView) findViewById(R.id.tvKnowledgeTabSystem);
        tvKnowledgeTabSystem.setOnClickListener(this);

        tvKnowledgeTabNavigation = (MyTextView) findViewById(R.id.tvKnowledgeTabNavigation);
        tvKnowledgeTabNavigation.setOnClickListener(this);

        vpKnowledgeNavigation = (ViewPager) findViewById(R.id.vpKnowledgeNavigation);
    }

    @Override
    public void initData() {
        adapter=new FragmentListAdapter(getChildFragmentManager());
        List<Fragment> list=new ArrayList<>();
        list.add(KnowledgeSystemFragment.newInstance());
        list.add(NavigationFragment.newInstance());
        adapter.setList(list);
        vpKnowledgeNavigation.setAdapter(adapter);
        vpKnowledgeNavigation.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                selectTab(i);
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.tvKnowledgeTabSystem:
                vpKnowledgeNavigation.setCurrentItem(0);
                selectTab(0);
            break;
            case R.id.tvKnowledgeTabNavigation:
                vpKnowledgeNavigation.setCurrentItem(1);
                selectTab(1);
            break;
        }
    }
    private void selectTab(int selectIndex){
        SecondHelper viewHelper ;
        if(selectIndex==0){
            tvKnowledgeTabSystem.setTextColor(color(R.color.colorAccent));
            viewHelper = tvKnowledgeTabSystem.getViewHelper();
            viewHelper.setSolidColor(color(R.color.c_white)).complete();

            tvKnowledgeTabNavigation.setTextColor(color(R.color.c_white));
            viewHelper = tvKnowledgeTabNavigation.getViewHelper();
            viewHelper.setSolidColor(color(R.color.colorAccent)).complete();
        }else{
            tvKnowledgeTabNavigation.setTextColor(color(R.color.colorAccent));
            viewHelper = tvKnowledgeTabNavigation.getViewHelper();
            viewHelper.setSolidColor(color(R.color.c_white)).complete();

            tvKnowledgeTabSystem.setTextColor(color(R.color.c_white));
            viewHelper = tvKnowledgeTabSystem.getViewHelper();
            viewHelper.setSolidColor(color(R.color.colorAccent)).complete();
        }

    }
}
