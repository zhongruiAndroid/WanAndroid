package com.zr.wanandroid.module.home.activity;

import android.view.View;
import android.widget.FrameLayout;

import com.android.basecore.tools.ActTools;
import com.github.fastshape.MyRadioButton;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.module.home.fragment.HomeFragment;
import com.zr.wanandroid.module.home.presenter.HomeActPresenter;

public class HomeActivity extends BaseActivity<HomeActPresenter> {
    private FrameLayout flHomeContent;

    private MyRadioButton selectView;
    private MyRadioButton rbHomeTab;
    private MyRadioButton rbKnowledgeSystemTab;
    private MyRadioButton rbNavigationTab;
    private MyRadioButton rbOfficialTab;
    private MyRadioButton rbQuestionTab;
    private MyRadioButton rbMyTab;


    private HomeFragment homeFragment;
    @Override
    public int getContentView() {
        return R.layout.main_act;
    }

    @Override
    public void initView() {

        flHomeContent = findViewById(R.id.flHomeContent);
        rbHomeTab = findViewById(R.id.rbHomeTab);
        rbKnowledgeSystemTab = findViewById(R.id.rbKnowledgeSystemTab);
        rbNavigationTab = findViewById(R.id.rbNavigationTab);
        rbOfficialTab = findViewById(R.id.rbOfficialTab);
        rbQuestionTab = findViewById(R.id.rbQuestionTab);
        rbMyTab = findViewById(R.id.rbMyTab);

        selectView=rbHomeTab;
    }
    @Override
    public void initViewAfter() {
        rbHomeTab.setOnClickListener(this);
        rbKnowledgeSystemTab.setOnClickListener(this);
        rbNavigationTab.setOnClickListener(this);
        rbOfficialTab.setOnClickListener(this);
        rbQuestionTab.setOnClickListener(this);
        rbMyTab.setOnClickListener(this);
    }

    @Override
    public void initData() {
        homeFragment=HomeFragment.newInstance();
        ActTools.addFragment(this,R.id.flHomeContent,homeFragment);
    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.rbHomeTab:

            break;
            case R.id.rbKnowledgeSystemTab:

            break;
            case R.id.rbNavigationTab:

            break;
            case R.id.rbOfficialTab:

            break;
            case R.id.rbQuestionTab:

            break;
            case R.id.rbMyTab:

            break;
        }
    }
}
