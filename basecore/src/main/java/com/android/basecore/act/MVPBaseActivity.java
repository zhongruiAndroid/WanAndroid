package com.android.basecore.act;

import android.os.Bundle;

import com.android.basecore.presenter._BasePresenter;

public abstract class MVPBaseActivity<P extends _BasePresenter> extends _BaseActivity{
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mPresenter=getClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    public P getPresenter() {
        return mPresenter;
    }
}
