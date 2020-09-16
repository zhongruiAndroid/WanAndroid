package com.android.basecore.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.basecore.act._BaseActivity;
import com.android.basecore.presenter._BasePresenter;
import com.android.basecore.tools.AutoInstance;

public abstract class MVPBaseFragment<P extends _BasePresenter> extends _BaseFragment{
    protected P mPresenter;
    protected P createPresenter(){
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
