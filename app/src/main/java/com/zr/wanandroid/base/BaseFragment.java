package com.zr.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.basecore.fragment.MVPBaseFragment;
import com.android.basecore.presenter._BasePresenter;
import com.github.progresslayout.ProgressFrameLayout;
import com.github.progresslayout.ProgressInter;
import com.github.progresslayout.ProgressLinearLayout;
import com.github.progresslayout.ProgressListener;
import com.github.progresslayout.ProgressRelativeLayout;
import com.zr.wanandroid.R;
import com.zr.wanandroid.widget.TitleView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public abstract class BaseFragment<P extends _BasePresenter> extends MVPBaseFragment<P> implements ProgressListener.ErrorOnClickListener, ProgressListener.NoNetworkOnClickListener{


    private ProgressInter progressInter;
    protected PtrClassicFrameLayout pcflRefresh;
    public TitleView titleView;

    @Override
    protected final void initViewPrevious() {
        titleView = getView().findViewById(R.id.titleView);
    }
    public <T extends View>T findViewById( @IdRes int id){
        return  (T)getView().findViewById(id);
    }
    @Override
    public void initViewAfter() {
        View pcflRefreshView = getView().findViewById(R.id.pcflRefresh);
        if (null !=pcflRefreshView) {
            pcflRefresh = (PtrClassicFrameLayout) pcflRefreshView;
            pcflRefresh.setLastUpdateTimeRelateObject(this);
//            pcfl.disableWhenHorizontalMove(true);
            pcflRefresh.setYOffsetMultiple(3);
            pcflRefresh.setXOffsetMultiple(3);
            pcflRefresh.setScaledTouchMultiple(0.5f);
            pcflRefresh.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                    getOtherData();
                    getData(1,false);
                }
            });
        }
        View loadView = getView().findViewById(R.id.plLoad);
        if (null != loadView) {
            if(loadView instanceof ProgressRelativeLayout ||loadView instanceof ProgressLinearLayout ||loadView instanceof ProgressFrameLayout){
                progressInter= (ProgressInter) loadView;
                progressInter.setNoNetworkOnClickListener(this);
                progressInter.setErrorOnClickListener(this);
            }
        }
    }

    protected void getOtherData() {
    }
    protected void getData(int page,final boolean isLoad) {
    }

    public void showError(){
        if (progressInter != null) {
            progressInter.showError();
        }
    }

    public void showEmpty(){
        if (progressInter != null) {
            progressInter.showEmpty();
        }

    }

    public void showProgress(){
        if (progressInter != null) {
            progressInter.showProgress();
        }

    }

    public void showContent(){
        if (progressInter != null) {
            progressInter.showContent();
        }

    }
    public void showNoNetwork(){
        if (progressInter != null) {
            progressInter.showNoNetwork();
        }

    }
    @Override
    public void errorOnClick() {

    }
    @Override
    public void noNetworkOnClick() {

    }

}
