package com.zr.wanandroid.module.officialaccount.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.officialaccount.presenter.OfficialAccountPresenter;

public class OfficialAccountFragment extends BaseFragment<OfficialAccountPresenter> {
    private RecyclerView rvOfficialAccount;
    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public void initView() {
        rvOfficialAccount = (RecyclerView) findViewById(R.id.rvOfficialAccount);

        rvOfficialAccount.setLayoutManager(new GridLayoutManager(mActivity,3));
        rvOfficialAccount.addItemDecoration(new BaseItemDivider(mActivity,3));

        rvOfficialAccount.setAdapter(getPresenter().initAdapter());

    }
    public static OfficialAccountFragment newInstance() {
        Bundle args = new Bundle();
        OfficialAccountFragment fragment = new OfficialAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(page,isLoad);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
