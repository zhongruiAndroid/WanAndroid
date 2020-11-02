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
                if (event.isKnowledgeSystem) {
                    rvKnowledgeSystemListClassify.setTag(true);
                    rvKnowledgeSystemListClassifySecond.smoothScrollToPosition(event.position);
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {

        getTitleView().setAppTitle("知识体系");
        getTitleView().setAppTitleBackground(color(R.color.colorAccent));
        getTitleView().setAppTitleColor(color(R.color.c_white));
        getTitleView().setAppBackIcon(null);

        rvKnowledgeSystemListClassify = findViewById(R.id.rvKnowledgeSystemListClassify);
        rvKnowledgeSystemListClassifySecond = findViewById(R.id.rvKnowledgeSystemListClassifySecond);
        rvKnowledgeSystemListClassifySecond.setOnScrollListener(new RecyclerView.OnScrollListener() {

            private LinearLayoutManager layoutManager;
            private LinearLayoutManager layoutManagerSecond;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    Object tag = rvKnowledgeSystemListClassify.getTag();
                    if(tag!=null){
                        rvKnowledgeSystemListClassify.setTag(null);
                        return;
                    }
                    if (layoutManagerSecond == null) {
                        layoutManagerSecond = (LinearLayoutManager) rvKnowledgeSystemListClassifySecond.getLayoutManager();
                    }
                    int itemPosition = layoutManagerSecond.findFirstCompletelyVisibleItemPosition();
                    knowledgeSystemAdapter.setSelectPosition(itemPosition);

                    if (layoutManager == null) {
                        layoutManager = (LinearLayoutManager) rvKnowledgeSystemListClassify.getLayoutManager();
                    }
                    int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    if(lastCompletelyVisibleItemPosition<itemPosition){
                        rvKnowledgeSystemListClassify.smoothScrollToPosition(itemPosition);
                    }else{
                        int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                        if(firstCompletelyVisibleItemPosition>itemPosition){
                            rvKnowledgeSystemListClassify.smoothScrollToPosition(itemPosition);
                        }
                    }
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
