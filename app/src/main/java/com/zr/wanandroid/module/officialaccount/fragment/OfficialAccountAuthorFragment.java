package com.zr.wanandroid.module.officialaccount.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.android.basecore.listener.NoDoubleClickListener;
import com.github.dividerline.BaseItemDivider;
import com.github.interbus.BusCallback;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.bridge.ActBridge;
import com.zr.wanandroid.common.adapter.FragmentListAdapter;
import com.zr.wanandroid.module.officialaccount.bean.OfficialAccountBean;
import com.zr.wanandroid.module.officialaccount.event.GetArticleByAuthorEvent;
import com.zr.wanandroid.module.officialaccount.presenter.OfficialAccountPresenter;

import java.util.ArrayList;
import java.util.List;

public class OfficialAccountAuthorFragment extends BaseFragment<OfficialAccountPresenter> {
    private TabLayout tlOfficialAuthor;
    private ViewPager vpOfficialArticle;
    private DrawerLayout  dlOfficialAuthor;
    private RecyclerView    rvOfficialAccountAuthor;
    private FragmentListAdapter fragmentListAdapter;
    private List<Fragment> list;
    private int currentSelectPosition;
    private List<String> titleList;

    @Override
    public int getContentView() {
        return R.layout.official_account_author_frag;
    }

    @Override
    public void initView() {
        getTitleView().setAppTitle("公众号");
        getTitleView().setAppRightImg(R.drawable.home_search, new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View var1) {
                OfficialAccountArticleFragment fragment = (OfficialAccountArticleFragment) list.get(currentSelectPosition);
                ActBridge.toOfficialAccountArticleActivity(mActivity,fragment.getAuthorId(),titleList.get(currentSelectPosition));
            }
        });
        getTitleView().setAppBackIcon(R.drawable.official_menu);
        getTitleView().setAppBackClickListener(this);
        titleView.setAppTitleBackground(color(R.color.colorAccent));
        titleView.setAppTitleColor(color(R.color.c_white));

        dlOfficialAuthor = (DrawerLayout) findViewById(R.id.dlOfficialAuthor);
        rvOfficialAccountAuthor = (RecyclerView) findViewById(R.id.rvOfficialAccountAuthor);

        tlOfficialAuthor = findViewById(R.id.tlOfficialAuthor);
        vpOfficialArticle =   findViewById(R.id.vpOfficialArticle);

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
        setEvent(GetArticleByAuthorEvent.class, new BusCallback<GetArticleByAuthorEvent>() {
            @Override
            public void accept(GetArticleByAuthorEvent event) {
                showContent();
            }
        });
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
        switch (v.getId()){
            case R.id.appIbBack:
                dlOfficialAuthor.openDrawer(Gravity.LEFT);
            break;
        }
    }

    public void hiddenDrawerLayout() {
        dlOfficialAuthor.closeDrawers();
    }

    public void setOfficialAuthor(List<OfficialAccountBean> data) {
        fragmentListAdapter=new FragmentListAdapter(getChildFragmentManager());
        list = new ArrayList<>();
        titleList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            OfficialAccountBean bean = data.get(i);
            titleList.add(bean.getName());
            list.add(OfficialAccountArticleFragment.newInstance(i==0,bean.getId()));
        }
        fragmentListAdapter.setList(list);
        fragmentListAdapter.setTitleList(titleList);

        vpOfficialArticle.setAdapter(fragmentListAdapter);
        vpOfficialArticle.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                currentSelectPosition=i;
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tlOfficialAuthor.setupWithViewPager(vpOfficialArticle);
    }

    public void selectItem(int i) {
        vpOfficialArticle.setCurrentItem(i);
    }
}
