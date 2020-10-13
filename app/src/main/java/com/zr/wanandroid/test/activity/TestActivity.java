package com.zr.wanandroid.test.activity;

import android.view.View;
import android.widget.Button;

import com.android.basecore.tools.ActTools;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.bridge.ActBridge;
import com.zr.wanandroid.test.fragment.TestFragment;

public class TestActivity extends BaseActivity {
    Button btHidden;
    Button btShow;
    View tvTest;
    private TestFragment testFragment;

    @Override
    public int getContentView() {
        return R.layout.test_activity;
    }

    @Override
    public void initView() {
        testFragment = TestFragment.newInstance(1);
        ActTools.addFragment(mActivity, R.id.fl, testFragment);

        btHidden = findViewById(R.id.btHidden);
        btShow = findViewById(R.id.btShow);
        tvTest = findViewById(R.id.tvTest);
    }

    @Override
    public void initViewAfter() {
        btHidden.setOnClickListener(this);
        btShow.setOnClickListener(this);
        tvTest.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.btHidden:
                ActTools.hideFragment(mActivity,testFragment);
            break;
            case R.id.btShow:
                ActTools.showFragment(mActivity,testFragment);
            break;
            case R.id.tvTest:
                ActBridge.toEmptyActivity(mActivity);
            break;
        }
    }
}
