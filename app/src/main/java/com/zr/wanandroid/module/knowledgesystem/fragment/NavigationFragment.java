package com.zr.wanandroid.module.knowledgesystem.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.interbus.BusCallback;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.knowledgesystem.adapter.KnowledgeSystemAdapter;
import com.zr.wanandroid.module.knowledgesystem.event.SelectKnowledgeEvent;
import com.zr.wanandroid.module.knowledgesystem.presenter.NavigationPresenter;

public class NavigationFragment extends BaseFragment<NavigationPresenter> {
    private RecyclerView rvNavigationListClassify;
    private RecyclerView rvNavigationListClassifySecond;
    private KnowledgeSystemAdapter adapter;

    @Override
    public int getContentView() {
        return R.layout.navigation_frag;
    }

    public static NavigationFragment newInstance() {

        Bundle args = new Bundle();

        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initBus() {
        setEvent(SelectKnowledgeEvent.class, new BusCallback<SelectKnowledgeEvent>() {
            @Override
            public void accept(SelectKnowledgeEvent event) {
                if (!event.isKnowledgeSystem) {
                    rvNavigationListClassify.setTag(true);
                    rvNavigationListClassifySecond.smoothScrollToPosition(event.position);

                }
            }
        });
    }

    @Override
    public void initView() {
        showProgress();
        rvNavigationListClassify = (RecyclerView) findViewById(R.id.rvNavigationListClassify);
        rvNavigationListClassifySecond = (RecyclerView) findViewById(R.id.rvNavigationListClassifySecond);
        rvNavigationListClassifySecond.setOnScrollListener(new RecyclerView.OnScrollListener() {

            private LinearLayoutManager layoutManager;
            private LinearLayoutManager layoutManagerSecond;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Object tag = rvNavigationListClassify.getTag();
                    if (tag != null) {
                        rvNavigationListClassify.setTag(null);
                        return;
                    }
                    if (layoutManagerSecond == null) {
                        layoutManagerSecond = (LinearLayoutManager) rvNavigationListClassifySecond.getLayoutManager();
                    }
                    int itemPosition = layoutManagerSecond.findFirstCompletelyVisibleItemPosition();
                    adapter.setSelectPosition(itemPosition);


                    if (layoutManager == null) {
                        layoutManager = (LinearLayoutManager) rvNavigationListClassify.getLayoutManager();
                    }
                    int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    if (lastCompletelyVisibleItemPosition < itemPosition) {
                        if(itemPosition==-1){
                            itemPosition = layoutManagerSecond.findFirstVisibleItemPosition();
                        }
                        rvNavigationListClassify.smoothScrollToPosition(itemPosition);
                    } else {
                        int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                        if (firstCompletelyVisibleItemPosition > itemPosition) {
                            if(itemPosition==-1){
                                itemPosition = layoutManagerSecond.findFirstVisibleItemPosition()+1;
                                adapter.setSelectPosition(itemPosition);
                            }
                            rvNavigationListClassify.smoothScrollToPosition(itemPosition);
                        }
                    }

                }
            }
        });

        adapter = getPresenter().initAdapter();
        rvNavigationListClassify.setLayoutManager(new LinearLayoutManager(mActivity));
        rvNavigationListClassify.setAdapter(adapter);


        rvNavigationListClassifySecond.setLayoutManager(new LinearLayoutManager(mActivity));
        rvNavigationListClassifySecond.setAdapter(getPresenter().initSecondAdapter());

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
