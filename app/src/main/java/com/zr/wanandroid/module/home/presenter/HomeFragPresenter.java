package com.zr.wanandroid.module.home.presenter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.basecore.widget.adapter.CustomViewHolder;
import com.android.basecore.widget.adapter.LoadInter;
import com.android.basecore.widget.adapter.LoadMoreAdapter;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.module.home.activity.HomeActivity;
import com.zr.wanandroid.module.home.fragment.HomeFragment;
import com.zr.wanandroid.module.home.model.HomeModel;

public class HomeFragPresenter extends BasePresenter<HomeFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private LoadMoreAdapter adapter;
    public void test(){
    }

    public RecyclerView.Adapter initAdapter() {
        adapter=new LoadMoreAdapter(R.layout.home_article_item,getView().pageSize) {
            @Override
            public void bindData(CustomViewHolder holder, int position, Object item) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }

    public void getData(int page, boolean isLoad) {
        HomeModel.getInstance().getHomeTopArticleList(null);
    }

    @Override
    public void onViewStateShowForFragment(boolean isFirstShow) {

        HomeModel.getInstance().getHomeTopArticleList(null);

    }

    @Override
    public void loadMore(LoadInter loadInter) {
    }
}
