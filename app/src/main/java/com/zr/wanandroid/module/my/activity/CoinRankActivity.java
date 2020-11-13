package com.zr.wanandroid.module.my.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.module.my.presenter.CoinRankPresenter;

public class CoinRankActivity extends BaseActivity<CoinRankPresenter> {
    private RecyclerView rvCoinRank;
    @Override
    public int getContentView() {
        return R.layout.coin_rank_act;
    }

    @Override
    public void initView() {

        titleView.setAppTitle("积分排行");
        rvCoinRank = findViewById(R.id.rvCoinRank);
        rvCoinRank.setLayoutManager(new LinearLayoutManager(mActivity));
        BaseItemDivider baseItemDivider = new BaseItemDivider(mActivity, 2, color(R.color.c_divider));
        baseItemDivider.setSkipEndCount(2);
        rvCoinRank.addItemDecoration(baseItemDivider);

        rvCoinRank.setAdapter(getPresenter().initAdapter());

    }

    @Override
    public void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getCoinRank(1,isLoad);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
