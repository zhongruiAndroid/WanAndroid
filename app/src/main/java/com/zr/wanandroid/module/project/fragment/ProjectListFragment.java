package com.zr.wanandroid.module.project.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.developtools.DensityUtils;
import com.github.dividerline.BaseItemDivider;
import com.github.interbus.InterBus;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.project.event.GetProjectListEvent;
import com.zr.wanandroid.module.project.presenter.ProjectListPresenter;

public class ProjectListFragment extends BaseFragment<ProjectListPresenter> {
    public static final String PARAM_ID="PARAM_ID";
    public static final String PARAM_IS_FIRST="PARAM_IS_FIRST";

    private RecyclerView rvProjectList;
    @Override
    public int getContentView() {
        return R.layout.project_list_frag;
    }

    public static ProjectListFragment newInstance(boolean isFirst,String id) {

        Bundle args = new Bundle();
        args.putBoolean(PARAM_IS_FIRST,isFirst);
        args.putString(PARAM_ID,id);
        ProjectListFragment fragment = new ProjectListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        showProgress();

        rvProjectList = findViewById(R.id.rvProjectList);

        rvProjectList.setLayoutManager(new LinearLayoutManager(mActivity));
        BaseItemDivider baseItemDivider = new BaseItemDivider(getContext(), DensityUtils.dp2px(20));
        baseItemDivider.setSkipEndCount(2);
        rvProjectList.addItemDecoration(baseItemDivider);
        rvProjectList.setAdapter(getPresenter().initAdapter());

    }

    @Override
    public void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        String id = getArguments().getString(PARAM_ID);
        getPresenter().getData(1,false,id);
    }
    public void getProjectSuccess() {
        boolean isFirst = getArguments().getBoolean(PARAM_IS_FIRST);
        if (isFirst) {
            InterBus.get().post(new GetProjectListEvent());
        }
    }
    @Override
    public void onNoDoubleClick(View v) {

    }
}
