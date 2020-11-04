package com.zr.wanandroid.module.project.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.text.HtmlCompat;
import android.support.v4.view.ViewPager;
import android.text.Spanned;
import android.view.View;

import com.github.interbus.BusCallback;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.common.adapter.FragmentListAdapter;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;
import com.zr.wanandroid.module.project.event.GetProjectListEvent;
import com.zr.wanandroid.module.project.presenter.ProjectClassifyPresenter;
import com.zr.wanandroid.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class ProjectClassifyFragment extends BaseFragment<ProjectClassifyPresenter> {
    private TabLayout tlProjectClassify;
    private MyViewPager vpProjectClassify;
    private FragmentListAdapter adapter;
    private List<Fragment> list;
    private List<String> titleList;

    @Override
    public int getContentView() {
        return R.layout.project_classify_frag;
    }

    public static ProjectClassifyFragment newInstance() {

        Bundle args = new Bundle();

        ProjectClassifyFragment fragment = new ProjectClassifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {

        tlProjectClassify = findViewById(R.id.tlProjectClassify);
        vpProjectClassify = findViewById(R.id.vpProjectClassify);
    }

    @Override
    public void initBus() {
        setEvent(GetProjectListEvent.class, new BusCallback<GetProjectListEvent>() {
            @Override
            public void accept(GetProjectListEvent event) {
                showContent();
            }
        });
    }

    @Override
    public void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(page, isLoad);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }

    public void setProjectClassifyData(List<KnowledgeSystemBean> data) {
        adapter = new FragmentListAdapter(getChildFragmentManager());
        list = new ArrayList<>();
        titleList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            KnowledgeSystemBean bean = data.get(i);
            list.add(ProjectListFragment.newInstance(i == 0, bean.getId()));
            Spanned spanned = HtmlCompat.fromHtml(bean.getName(), HtmlCompat.FROM_HTML_MODE_LEGACY);
            titleList.add(spanned.toString());
        }
        adapter.setList(list);
        adapter.setTitleList(titleList);


        vpProjectClassify.setAdapter(adapter);

        tlProjectClassify.setupWithViewPager(vpProjectClassify);
    }
}
