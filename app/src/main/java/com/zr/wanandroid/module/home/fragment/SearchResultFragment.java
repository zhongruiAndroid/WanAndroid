package com.zr.wanandroid.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.developtools.DensityUtils;
import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.presenter.SearchResultPresenter;

public class SearchResultFragment extends BaseFragment<SearchResultPresenter> {
    public static final String SEARCH_TEXT="SEARCH_TEXT";
    private android.support.v7.widget.RecyclerView rvHomeSearch;

    @Override
    public int getContentView() {
        return R.layout.search_result_frag;
    }

    @Override
    public void initView() {
        rvHomeSearch = findViewById(R.id.rvHomeSearch);
        rvHomeSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHomeSearch.addItemDecoration(new BaseItemDivider(getContext(), DensityUtils.dp2px(20)));
        HomeAdapter adapter = getPresenter().initAdapter();
        rvHomeSearch.setAdapter(adapter);
    }
    public static SearchResultFragment newInstance(String searchText) {
        Bundle args = new Bundle();
        SearchResultFragment fragment = new SearchResultFragment();
        args.putString(SEARCH_TEXT,searchText);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initData() {
        showProgress();
        String text = getArguments().getString(SEARCH_TEXT);
        getPresenter().getData(1,text,false);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
    public void searchByText(String searchText) {
        showProgress();
        getPresenter().getData(1,searchText,false);
    }
}
