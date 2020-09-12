package com.look.book.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.basecore.tools.ActTools;
import com.look.book.R;
import com.look.book.base.BaseFragment;
import com.look.book.bridge.ActBridge;

public class TestFragment extends BaseFragment {
    TextView tvTest;
    Button btTestClick;
    @Override
    public int getContentView() {
        return R.layout.test_fragment;
    }

    public static TestFragment newInstance(int index) {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        args.putString("index",index+"");
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        tvTest=findViewById(R.id.tvTest);
        btTestClick=findViewById(R.id.btTestClick);
    }


    @Override
    public void initViewAfter() {
        btTestClick.setOnClickListener(this);
        tvTest.setText("testFragment"+getIndex());
    }
    public String getIndex(){
        return getArguments().getString("index");
    }
    @Override
    public void initData() {
        Log.i("=====",getIndex()+"===initData===");
    }
    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.btTestClick:
                ActBridge.toEmptyActivity(mActivity);
            break;
        }
    }

    @Override
    public void onViewClick(View v) {
        super.onViewClick(v);
    }

    @Override
    protected void onViewStateShow(boolean isFirstShow) {
        super.onViewStateShow(isFirstShow);
        Log.i("=====",getIndex()+"===onViewStateShow==="+isFirstShow);
    }
    @Override
    protected void onViewStateHidden(boolean isFirstHidden) {
        super.onViewStateHidden(isFirstHidden);
        Log.i("=====",getIndex()+"===onViewStateHidden==="+isFirstHidden);
    }

    @Override
    public boolean needLazyLoad() {
        return false;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("=====",getIndex()+"===onCreateView==aaa===");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i("=====",getIndex()+"===onViewCreated==bbb===");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("=====",getIndex()+"===onDestroyView===");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("=====",getIndex()+"===onDestroy===");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("=====",getIndex()+"===onCreate===");
    }
}
