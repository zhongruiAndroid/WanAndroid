package com.zr.wanandroid.module.home.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.android.basecore.widget.adapter.CustomViewHolder;
import com.android.basecore.widget.adapter.LoadMoreAdapter;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.module.home.activity.HomeActivity;
import com.zr.wanandroid.module.home.fragment.HomeFragment;

public class HomeFragPresenter extends BasePresenter<HomeFragment> {
    private LoadMoreAdapter adapter;
    public void test(){
    }

    public RecyclerView.Adapter initAdapter() {
        adapter=new LoadMoreAdapter(R.layout.home_article_item,getView().pageSize) {
            @Override
            public void bindData(CustomViewHolder holder, int position, Object item) {

            }
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }
        };
        return adapter;
    }

    public void getData(int page, boolean isLoad) {

    }
}
