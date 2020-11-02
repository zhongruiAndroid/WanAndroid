package com.zr.wanandroid.module.officialaccount.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.officialaccount.presenter.OfficialAccountPresenter;

public class OfficialAccountAuthorFragment extends BaseFragment<OfficialAccountPresenter> {
    private RecyclerView rvOfficialAccount;
    @Override
    public int getContentView() {
        return R.layout.official_account_author_frag;
    }

    @Override
    public void initView() {
        getTitleView().setAppTitle("公众号");
        getTitleView().setAppBackIcon(null);
        titleView.setAppTitleBackground(color(R.color.colorAccent));
        titleView.setAppTitleColor(color(R.color.c_white));

        rvOfficialAccount = (RecyclerView) findViewById(R.id.rvOfficialAccount);

        rvOfficialAccount.setLayoutManager(new GridLayoutManager(mActivity,3));
        rvOfficialAccount.addItemDecoration(new BaseItemDivider(mActivity,3,color(R.color.c_999999)));

        rvOfficialAccount.setAdapter(getPresenter().initAdapter());

    }
    public static OfficialAccountAuthorFragment newInstance() {
        Bundle args = new Bundle();
        OfficialAccountAuthorFragment fragment = new OfficialAccountAuthorFragment();
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
