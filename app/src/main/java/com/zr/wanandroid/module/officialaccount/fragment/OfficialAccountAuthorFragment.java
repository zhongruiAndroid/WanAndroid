package com.zr.wanandroid.module.officialaccount.fragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.officialaccount.presenter.OfficialAccountPresenter;

public class OfficialAccountAuthorFragment extends BaseFragment<OfficialAccountPresenter> {
    private RecyclerView rvOfficialArticle;
    private DrawerLayout  dlOfficialAuthor;
    private RecyclerView    rvOfficialAccountAuthor;
    @Override
    public int getContentView() {
        return R.layout.official_account_author_frag;
    }

    @Override
    public void initView() {
        getTitleView().setAppTitle("公众号");
        getTitleView().setAppBackIcon(R.drawable.official_menu);
        getTitleView().setAppBackClickListener(this);
        titleView.setAppTitleBackground(color(R.color.colorAccent));
        titleView.setAppTitleColor(color(R.color.c_white));

        dlOfficialAuthor = (DrawerLayout) findViewById(R.id.dlOfficialAuthor);
        rvOfficialAccountAuthor = (RecyclerView) findViewById(R.id.rvOfficialAccountAuthor);
        rvOfficialArticle = (RecyclerView) findViewById(R.id.rvOfficialArticle);

        rvOfficialAccountAuthor.setLayoutManager(new LinearLayoutManager(mActivity));
        rvOfficialAccountAuthor.addItemDecoration(new BaseItemDivider(mActivity,2,color(R.color.c_divider)));
        rvOfficialAccountAuthor.setAdapter(getPresenter().initAdapter());

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
    public void initBus() {
        super.initBus();
    }

    @Override
    public void initData() {
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(page,isLoad);
    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.appIbBack:
                dlOfficialAuthor.openDrawer(Gravity.LEFT);
            break;
        }
    }

    public void hiddenDrawerLayout() {
        dlOfficialAuthor.closeDrawers();
    }
}
