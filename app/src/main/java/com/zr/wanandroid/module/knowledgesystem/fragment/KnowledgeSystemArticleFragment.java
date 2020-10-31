package com.zr.wanandroid.module.knowledgesystem.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.developtools.DensityUtils;
import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.knowledgesystem.presenter.KnowledgeSystemArticleFragmentPresenter;

public class KnowledgeSystemArticleFragment extends BaseFragment<KnowledgeSystemArticleFragmentPresenter> {
    public static final String PARAM_ID="PARAM_ID";
    private RecyclerView rvKnowledgeSystemArticleList;
    @Override
    public int getContentView() {
        return R.layout.knowlegdge_system_article_frag;
    }

    public static KnowledgeSystemArticleFragment newInstance(String cid) {
        Bundle args = new Bundle();
        args.putString(PARAM_ID,cid);
        KnowledgeSystemArticleFragment fragment = new KnowledgeSystemArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        showProgress();
        rvKnowledgeSystemArticleList = (RecyclerView) findViewById(R.id.rvKnowledgeSystemArticleList);

        rvKnowledgeSystemArticleList.setLayoutManager(new LinearLayoutManager(mActivity));

        BaseItemDivider baseItemDivider = new BaseItemDivider(getContext(), DensityUtils.dp2px(20));
        baseItemDivider.setSkipEndCount(2);
        rvKnowledgeSystemArticleList.addItemDecoration(baseItemDivider);
        rvKnowledgeSystemArticleList.setAdapter(getPresenter().initAdapter());

    }

    @Override
    public void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(page,getArguments().getString(PARAM_ID),isLoad);
    }
    @Override
    public void onNoDoubleClick(View v) {

    }
}
