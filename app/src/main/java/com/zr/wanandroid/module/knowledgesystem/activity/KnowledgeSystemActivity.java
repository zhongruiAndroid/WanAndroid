package com.zr.wanandroid.module.knowledgesystem.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.common.adapter.FragmentListAdapter;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;
import com.zr.wanandroid.module.knowledgesystem.fragment.KnowledgeSystemArticleFragment;
import com.zr.wanandroid.module.knowledgesystem.presenter.KnowledgeSystemArticleFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeSystemActivity extends BaseActivity<KnowledgeSystemArticleFragmentPresenter> {
    public static final String INTENT_KNOWLEDGE_SYSTEM="INTENT_KNOWLEDGE_SYSTEM";
    private TabLayout tlKnowledgeSystemTab;
    private ViewPager vpKnowledgeSystem;
    @Override
    public int getContentView() {
        return R.layout.knowledge_system_act;
    }

    @Override
    public void initView() {
        tlKnowledgeSystemTab = findViewById(R.id.tlKnowledgeSystemTab);
        vpKnowledgeSystem = findViewById(R.id.vpKnowledgeSystem);

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        KnowledgeSystemBean knowledgeSystemBean = (KnowledgeSystemBean) intent.getSerializableExtra(INTENT_KNOWLEDGE_SYSTEM);
        if(knowledgeSystemBean==null){
            return;
        }
        titleView.setAppTitle(knowledgeSystemBean.getName());

        FragmentListAdapter adapter=new FragmentListAdapter(getSupportFragmentManager());
        List<Fragment> list=new ArrayList<>();
        List<String> title=new ArrayList<>();
        for (KnowledgeSystemBean.ChildrenBean childrenBean:knowledgeSystemBean.getChildren()){

            list.add(KnowledgeSystemArticleFragment.newInstance(childrenBean.getId()));
            title.add(childrenBean.getName());
        }
        adapter.setTitleList(title);
        adapter.setList(list);

        vpKnowledgeSystem.setAdapter(adapter);
        tlKnowledgeSystemTab.setupWithViewPager(vpKnowledgeSystem);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
