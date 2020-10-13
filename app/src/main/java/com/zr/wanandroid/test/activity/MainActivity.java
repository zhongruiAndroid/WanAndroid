package com.zr.wanandroid.test.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.bridge.ActBridge;

public class MainActivity extends BaseActivity {
    Button btTest1;
    Button btTest2;
    Button btTest3;
    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        btTest1=findViewById(R.id.btTest1);
        btTest2=findViewById(R.id.btTest2);
        btTest3=findViewById(R.id.btTest3);
    }

    @Override
    public void initViewAfter() {

        btTest1.setOnClickListener(this);
        btTest2.setOnClickListener(this);
        btTest3.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()){
            case R.id.btTest1:
                ActBridge.toTestActivity(mActivity);
            break;
            case R.id.btTest2:
                ActBridge.toTest2Activity(mActivity);
            break;
            case R.id.btTest3:
                ActBridge.toTest3Activity(mActivity);
            break;
        }
    }

    @Override
    protected void onResume(boolean isFirstInto) {
        super.onResume(isFirstInto);
        Log.i("=====","====isFirstInto="+isFirstInto);
    }

    @Override
    protected void onStop(boolean isFirstHidden) {
        super.onStop(isFirstHidden);
        Log.i("=====","====isFirstHidden="+isFirstHidden);
    }
}
