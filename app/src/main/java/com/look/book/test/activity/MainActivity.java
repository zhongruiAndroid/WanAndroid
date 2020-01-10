package com.look.book.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.look.book.R;
import com.look.book.base.BaseActivity;
import com.look.book.bridge.ActBridge;

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
    public void setViewListener() {
        btTest1.setOnClickListener(this);
        btTest2.setOnClickListener(this);
        btTest3.setOnClickListener(this);
    }

    @Override
    public void initViewAfter() {

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
