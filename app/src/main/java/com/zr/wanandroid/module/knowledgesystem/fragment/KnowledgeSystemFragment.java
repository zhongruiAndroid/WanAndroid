package com.zr.wanandroid.module.knowledgesystem.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.interbus.BusCallback;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.knowledgesystem.adapter.KnowledgeSystemAdapter;
import com.zr.wanandroid.module.knowledgesystem.event.SelectKnowledgeEvent;
import com.zr.wanandroid.module.knowledgesystem.presenter.KnowledgeSystemFragmentPresenter;

public class KnowledgeSystemFragment extends BaseFragment<KnowledgeSystemFragmentPresenter> {
    private RecyclerView rvKnowledgeSystemListClassify;
    private RecyclerView rvKnowledgeSystemListClassifySecond;
    private KnowledgeSystemAdapter knowledgeSystemAdapter;

    @Override
    public int getContentView() {
        return R.layout.knowlegdge_system_frag;
    }

    public static KnowledgeSystemFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeSystemFragment fragment = new KnowledgeSystemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initBus() {
        setEvent(SelectKnowledgeEvent.class, new BusCallback<SelectKnowledgeEvent>() {
            @Override
            public void accept(SelectKnowledgeEvent event) {
                rvKnowledgeSystemListClassifySecond.smoothScrollToPosition(event.position);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {

        titleView.setAppTitle("知识体系");
        titleView.setAppTitleBackground(color(R.color.colorAccent));
        titleView.setAppTitleColor(color(R.color.c_white));
        titleView.setAppBackIcon(null);

        rvKnowledgeSystemListClassify = findViewById(R.id.rvKnowledgeSystemListClassify);
        rvKnowledgeSystemListClassifySecond = findViewById(R.id.rvKnowledgeSystemListClassifySecond);
        rvKnowledgeSystemListClassifySecond.setOnScrollListener(new RecyclerView.OnScrollListener() {

            private LinearLayoutManager layoutManager;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    if (layoutManager == null) {
                        layoutManager = (LinearLayoutManager) rvKnowledgeSystemListClassifySecond.getLayoutManager();
                    }
                    int itemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    knowledgeSystemAdapter.setSelectPosition(itemPosition);
                }
            }
        });

        knowledgeSystemAdapter = getPresenter().initAdapter();
        rvKnowledgeSystemListClassify.setLayoutManager(new LinearLayoutManager(mActivity));
        rvKnowledgeSystemListClassify.setAdapter(knowledgeSystemAdapter);


        rvKnowledgeSystemListClassifySecond.setLayoutManager(new LinearLayoutManager(mActivity));
        rvKnowledgeSystemListClassifySecond.setAdapter(getPresenter().initSecondAdapter());




    }



    @Override
    public void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(1, false);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
