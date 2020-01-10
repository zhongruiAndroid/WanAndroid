package com.android.basecore.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.basecore.tools.ClickTools;

public abstract class _BaseFragment extends Fragment implements View.OnClickListener {
    private final String FLAG_LAZY_IS_FIRST_LOAD = "FLAG_LAZY_IS_FIRST_LOAD";
    private final String FLAG_LAZY_IS_DESTROY_VIEW = "FLAG_LAZY_IS_DESTROY_VIEW";
    private final String FRAGMENT_VIEW_STATE = "FRAGMENT_VIEW_STATE";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAttr();
        if (savedInstanceState != null) {
            boolean isNeedHidden = savedInstanceState.getBoolean(FRAGMENT_VIEW_STATE);
            FragmentTransaction ft = this.getFragmentManager().beginTransaction();
            if (isNeedHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (outState != null) {
            outState.putBoolean(FRAGMENT_VIEW_STATE, this.isHidden());
        }
        super.onSaveInstanceState(outState);
    }


    private Long clickTimeFlag;
    protected FragmentActivity mActivity;
    protected View mView;
    protected boolean isFirstShow = true;
    protected boolean isFirstHidden = true;
    private boolean isDestroyed = false;

    public abstract @LayoutRes
    int getContentView();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isDestroyed = false;
        int contentViewId = getContentView();
        if (contentViewId > 0) {
            mView = inflater.inflate(contentViewId, container, false);
        } else {
            mView = getContentLayout();
        }
        initView();
        setViewListener();
        initViewAfter();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isFirstShow=true;
        isFirstHidden=true;
        setIsCreateView(true);
        if(needLazyLoad()){
            if(getUserVisibleHint()&&isFirstLoadData()){
                setIsFirstLoadData(false);
                initData();
            }
        }else{
            initData();
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!needLazyLoad()) {
            return;
        }
        if (isCreateView() && isVisibleToUser && isFirstLoadData()) {
            setIsFirstLoadData(false);
            initData();
        }
        if(!isVisibleToUser){
            onHidden(isFirstHidden);
            if(isFirstHidden){
                isFirstHidden=false;
            }
        }
    }

    private void setIsFirstLoadData(boolean value) {
        if (getArguments() == null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(FLAG_LAZY_IS_FIRST_LOAD, value);
            setArguments(bundle);
        } else {
            getArguments().putBoolean(FLAG_LAZY_IS_FIRST_LOAD, value);
        }
    }
    private boolean isFirstLoadData(){
        if (getArguments() == null) {
            return true;
        }
        return getArguments().getBoolean(FLAG_LAZY_IS_FIRST_LOAD, true);
    }
    private void setIsCreateView(boolean value) {
        if(getArguments()==null){
            Bundle bundle=new Bundle();
            bundle.putBoolean(FLAG_LAZY_IS_DESTROY_VIEW,value);
            setArguments(bundle);
        }else{
            getArguments().putBoolean(FLAG_LAZY_IS_DESTROY_VIEW,value);
        }
    }
    private boolean isCreateView() {
        if(getArguments()==null){
            return false;
        }
        return getArguments().getBoolean(FLAG_LAZY_IS_DESTROY_VIEW,false);
    }
    public boolean needLazyLoad() {
        return true;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        resetTempViewFlag();
        ClickTools.get().removeLastClickTime(clickTimeFlag);
    }
    private void resetTempViewFlag() {
        setIsCreateView(false);
        setIsFirstLoadData(true);
    }

    public abstract void initView();

    public abstract void setViewListener();

    public abstract void initViewAfter();

    public abstract void initData();


    public long getNoDoubleClickInterval() {
        return 900;
    }


    public abstract void onNoDoubleClick(View view);

    public void onViewClick(View view) {
    }

    public View getContentLayout() {
        TextView textView = new TextView(mActivity);
        textView.setText("getContentLayout()");
        return textView;
    }

    private void initAttr() {
        mActivity = getActivity();
        clickTimeFlag = new Long(hashCode());
    }

    protected void _setTheme(int themeId) {
        if (themeId <= 0) {
            return;
        }
        if (isDestroy()) {
            return;
        }
        mActivity.setTheme(themeId);
    }

    @Override
    public void onResume() {
        super.onResume();
        isDestroyed = false;
        if(!isHidden()){
            this.onResume(isFirstShow);
            if(isFirstShow){
                this.isFirstShow=false;
            }
        }
    }
    protected void onResume(boolean isFirstShow) {

    }
    protected void onHidden(boolean isFirstHidden) {

    }
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            this.onResume(isFirstShow);
            if(isFirstShow){
                this.isFirstShow=false;
            }
        }else{
            checkHidden();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        ClickTools.get().clearLastClickTime(clickTimeFlag);
    }

    @Override
    public void onStop() {
        super.onStop();
        checkHidden();
    }
    private void checkHidden(){
        if(isFirstShow){
            return;
        }
        onHidden(isFirstHidden);
        if(isFirstHidden){
            isFirstHidden=false;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ClickTools.get().removeLastClickTime(clickTimeFlag);
        isDestroyed = true;
    }

    @Override
    public final void onClick(View v) {
        onViewClick(v);
        boolean fastClick = ClickTools.get().isFastClick(clickTimeFlag, v, getNoDoubleClickInterval());
        if (!fastClick) {
            onNoDoubleClick(v);
        }
    }

    public boolean isDestroy() {
        if (isDestroyed) {
            return true;
        }
        if (mActivity == null) {
            return true;
        } else {
            boolean isDestroyed = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                isDestroyed = mActivity.isDestroyed();
            } else {
                if (mActivity instanceof FragmentActivity) {
                    FragmentManager supportFragmentManager = ((FragmentActivity) mActivity).getSupportFragmentManager();
                    if (supportFragmentManager != null) {
                        isDestroyed = supportFragmentManager.isDestroyed();
                    }
                }
            }
            return isDestroyed;
        }
    }
}