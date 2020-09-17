package com.android.basecore.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.basecore.act._BaseActivity;
import com.android.basecore.presenter._BasePresenter;
import com.android.basecore.tools.AutoInstance;

public abstract class MVPBaseFragment<P extends _BasePresenter> extends _BaseFragment{
    protected P mPresenter;
    protected P createPresenter(){
        return null;
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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter=createPresenter();
        if(mPresenter==null){
            mPresenter= AutoInstance.autoCreatePresenter(getClass());
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        if(mPresenter!=null){
            mPresenter.onResumeForFragment();
        }
        super.onResume();
    }

    @Override
    protected void onViewStateShow(boolean isFirstShow) {
        if(mPresenter!=null){
            mPresenter.onViewStateShowForFragment(isFirstShow);
        }
        super.onViewStateShow(isFirstShow);
    }

    @Override
    protected void onViewStateHidden(boolean isFirstHidden) {
        if(mPresenter!=null){
            mPresenter.onViewStateHiddenForFragment(isFirstHidden);
        }
        super.onViewStateHidden(isFirstHidden);
    }

    @Override
    public void onPause() {
        if(mPresenter!=null){
            mPresenter.onPause();
        }
        super.onPause();
    }

    @Override
    public void onStart() {
        if(mPresenter!=null){
            mPresenter.onStart();
        }
        super.onStart();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if(mPresenter!=null){
            mPresenter.onViewStateRestoredForFragment(savedInstanceState);
        }
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStop() {
        if(mPresenter!=null){
            mPresenter.onStopForFragment();
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if(mPresenter!=null){
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        if(mPresenter!=null){
            mPresenter.onDestroyViewForFragment();
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        if(mPresenter!=null){
            mPresenter.onDetachForFragment();
        }
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        if(mPresenter!=null){
            mPresenter.onAttachForFragment(context);
        }
        super.onAttach(context);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(mPresenter!=null){
            mPresenter.onConfigurationChanged(newConfig);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(mPresenter!=null){
            mPresenter.onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mPresenter!=null){
            mPresenter.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(mPresenter!=null){
            mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
