package com.zr.wanandroid.module.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.basecore.listener.NoDoubleClickListener;
import com.github.developtools.DensityUtils;
import com.github.developtools.N;
import com.github.fastshape.MyTextView;
import com.github.flowview.FlowLayout;
import com.github.interbus.InterBus;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.home.bean.SearchHotBean;
import com.zr.wanandroid.module.home.event.SearchArticleEvent;
import com.zr.wanandroid.module.home.helper.ViewHelper;
import com.zr.wanandroid.module.home.presenter.SearchHistoryPresenter;

import java.util.List;

public class SearchHistoryFragment extends BaseFragment<SearchHistoryPresenter> {
    private FlowLayout flHomeHotSearchRecord;
    private LinearLayout llHomeHistorySearch;
    private FlowLayout flHomeHistorySearchRecord;
    private View tvHomeHistorySearchClear;

    @Override
    public int getContentView() {
        return R.layout.search_history_frag;
    }

    public static SearchHistoryFragment newInstance() {

        Bundle args = new Bundle();

        SearchHistoryFragment fragment = new SearchHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {

        flHomeHotSearchRecord = findViewById(R.id.flHomeHotSearchRecord);
        llHomeHistorySearch = findViewById(R.id.llHomeHistorySearch);
        flHomeHistorySearchRecord = findViewById(R.id.flHomeHistorySearchRecord);
        tvHomeHistorySearchClear = findViewById(R.id.tvHomeHistorySearchClear);
        tvHomeHistorySearchClear.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getPresenter().getHotSearch();
        getPresenter().getHistorySearch();
    }
    public void getHistorySearch(){
        getPresenter().getHistorySearch();
    }
    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()) {
            case R.id.tvHomeHistorySearchClear:
                getPresenter().clearHistorySearchRecord();
                break;
        }
    }

    public void setHotSearchRecord(List<SearchHotBean> recordList) {
        addSearchRecord(flHomeHotSearchRecord, recordList);
    }

    public void setHistorySearchRecord(List<SearchHotBean> recordList) {
        if (N.isEmpty(recordList)) {
            ViewHelper.setVisibility(llHomeHistorySearch, View.INVISIBLE);
            ViewHelper.setVisibility(flHomeHistorySearchRecord, View.INVISIBLE);
            return;
        }
        ViewHelper.setVisibility(llHomeHistorySearch, View.VISIBLE);
        ViewHelper.setVisibility(flHomeHistorySearchRecord, View.VISIBLE);

        addSearchRecord(flHomeHistorySearchRecord, recordList);
    }

    private void addSearchRecord(FlowLayout flowLayout, List<SearchHotBean> recordList) {
        flowLayout.removeAllViews();
        for (SearchHotBean bean : recordList) {
            View recordView = getRecordView(bean);
            flowLayout.addView(recordView);
        }
    }

    private View getRecordView(SearchHotBean bean) {
        /*<com.github.fastshape.MyTextView
    android:layout_width="wrap_content"
    android:layout_height="30dp"
    android:text="面试"
    android:gravity="center"
    app:solidColor="@color/c_f5f5f5"
    android:textColor="@color/c_666666"
    app:radius="15dp"
    android:paddingLeft="10dp"
    android:paddingRight="10dp"
    android:textSize="13sp"
    />*/
        int dp10 = DensityUtils.dp2px(10);
        int dp30 = DensityUtils.dp2px(30);
        MyTextView textView = new MyTextView(mActivity);
        textView.setTextSize(13);
        textView.setLayoutParams(new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp30));
        textView.setGravity(Gravity.CENTER);
        textView.setText(bean.getName());
        textView.setPadding(dp10, 0, dp10, 0);
        textView.getViewHelper().setRadius(DensityUtils.dp2px(15)).setSolidColor(ContextCompat.getColor(getView().getContext(), R.color.c_transparent_5)).complete();
        textView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View var1) {
                InterBus.get().post(new SearchArticleEvent(textView.getText().toString()));
            }
        });
        return textView;

    }

    public void hiddenHistorySearch() {
        ViewHelper.setVisibility(llHomeHistorySearch, View.INVISIBLE);
        ViewHelper.setVisibility(flHomeHistorySearchRecord, View.INVISIBLE);
    }
}
