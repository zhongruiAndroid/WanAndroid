package com.zr.wanandroid.module.officialaccount.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.developtools.DensityUtils;
import com.github.dividerline.BaseItemDivider;
import com.github.interbus.InterBus;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.officialaccount.event.GetArticleByAuthorEvent;
import com.zr.wanandroid.module.officialaccount.presenter.OfficialAccountArticlePresenter;

public class OfficialAccountArticleFragment extends BaseFragment<OfficialAccountArticlePresenter> {
    public static final String PARAM_AUTHOR_ID = "PARAM_AUTHOR_ID";
    public static final String PARAM_IS_FIRST = "PARAM_IS_FIRST";
    private RecyclerView rvOfficialArticle;

    @Override
    public int getContentView() {
        return R.layout.official_account_article_frag;
    }

    public static OfficialAccountArticleFragment newInstance(boolean isFirst, String authorId) {

        Bundle args = new Bundle();
        args.putBoolean(PARAM_IS_FIRST, isFirst);
        args.putString(PARAM_AUTHOR_ID, authorId);
        OfficialAccountArticleFragment fragment = new OfficialAccountArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        showProgress();
        rvOfficialArticle = findViewById(R.id.rvOfficialArticle);


        rvOfficialArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseItemDivider baseItemDivider = new BaseItemDivider(getContext(), DensityUtils.dp2px(20));
        baseItemDivider.setSkipEndCount(2);
        rvOfficialArticle.addItemDecoration(baseItemDivider);
        rvOfficialArticle.setAdapter(getPresenter().initAdapter());

    }

    @Override
    public void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        String authorId = getArguments().getString(PARAM_AUTHOR_ID);
        getPresenter().getData(page, isLoad, authorId);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }

    public void getArticleSuccess() {
        boolean isFirst = getArguments().getBoolean(PARAM_IS_FIRST);
        if (isFirst) {
            InterBus.get().post(new GetArticleByAuthorEvent());
        }
    }
}
