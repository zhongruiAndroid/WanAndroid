package com.zr.wanandroid.module.my.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.fastshape.MyImageView;
import com.github.fastshape.MyTextView;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.bridge.ActBridge;
import com.zr.wanandroid.module.knowledgesystem.fragment.KnowledgeSystemFragment;
import com.zr.wanandroid.module.my.presenter.MyPresenter;

public class MyFragment extends BaseFragment<MyPresenter> {
    private ImageView ivMyRanking;
    private MyImageView ivMyHead;
    private TextView tvMyName;
    private TextView tvMyLevel;
    private MyTextView tvMyJiFen;
    private MyTextView tvMyCollect;
    private MyTextView tvMyShare;
    private MyTextView tvMyTodo;
    private MyTextView tvMyNight;
    private MyTextView tvMySetting;

    @Override
    public int getContentView() {
        return R.layout.my_frag;
    }

    @Override
    public void initView() {

        ivMyRanking = findViewById(R.id.ivMyRanking, true);
        ivMyHead = findViewById(R.id.ivMyHead, true);
        tvMyName = findViewById(R.id.tvMyName, true);
        tvMyLevel = findViewById(R.id.tvMyLevel, true);
        tvMyJiFen = findViewById(R.id.tvMyJiFen, true);
        tvMyCollect = findViewById(R.id.tvMyCollect, true);
        tvMyShare = findViewById(R.id.tvMyShare, true);
        tvMyTodo = findViewById(R.id.tvMyTodo, true);
        tvMyNight = findViewById(R.id.tvMyNight, true);
        tvMySetting = findViewById(R.id.tvMySetting, true);


    }

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.ivMyHead:
            case R.id.tvMyName:
                ActBridge.toLoginActivity(getActivity());
            break;
        }
    }
}
