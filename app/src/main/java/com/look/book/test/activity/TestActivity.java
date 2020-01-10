package com.look.book.test.activity;

import android.view.View;
import android.widget.Button;

import com.android.basecore.tools.ActTools;
import com.look.book.R;
import com.look.book.base.BaseActivity;
import com.look.book.test.fragment.TestFragment;

public class TestActivity extends BaseActivity {
    Button btHidden;
    Button btShow;
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
    }

    @Override
    public void setViewListener() {
        btHidden.setOnClickListener(this);
        btShow.setOnClickListener(this);
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
            case R.id.btHidden:
                ActTools.hideFragment(mActivity,testFragment);
            break;
            case R.id.btShow:
                ActTools.showFragment(mActivity,testFragment);
            break;
        }
    }
}
