package com.look.book.test.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.look.book.R;
import com.look.book.base.BaseFragment;

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
    public void setViewListener() {
        btTestClick.setOnClickListener(this);
    }

    @Override
    public void initViewAfter() {
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
                Log.i("=====",getIndex()+"===onNoDoubleClick===");
            break;
        }
    }

    @Override
    public void onViewClick(View v) {
        super.onViewClick(v);
        Log.i("=====",getIndex()+"===onViewClick===");
    }

    @Override
    protected void onResume(boolean isFirstShow) {
        super.onResume(isFirstShow);
        Log.i("=====",getIndex()+"===onResume==="+isFirstShow);
    }
    @Override
    protected void onHidden(boolean isFirstHidden) {
        super.onHidden(isFirstHidden);
        Log.i("=====",getIndex()+"===onHidden==="+isFirstHidden);
    }

    @Override
    public boolean needLazyLoad() {
        return true;
    }
}