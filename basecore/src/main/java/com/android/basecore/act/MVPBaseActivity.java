package com.android.basecore.act;

import android.os.Bundle;

import com.android.basecore.presenter._BasePresenter;
import com.android.basecore.tools.AutoInstance;

public abstract class MVPBaseActivity<P extends _BasePresenter> extends _BaseActivity{
    protected P mPresenter;
    protected P createPresenter(){
        return null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter=createPresenter();
        if(mPresenter==null){
            mPresenter= AutoInstance.autoCreatePresenter(getClass());
        }
        super.onCreate(savedInstanceState);


    }
    public P getPresenter() {
        if(mPresenter==null){
            mPresenter=createPresenter();
        }
        if(mPresenter==null){
            mPresenter=AutoInstance.autoCreatePresenter(getClass());
        }
        return mPresenter;
    }
}
