package com.android.basecore.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
        mActivity = getActivity();
        super.onCreate(savedInstanceState);
        initAttr();
        if (savedInstanceState != null) {
            boolean isNeedHidden = savedInstanceState.getBoolean(FRAGMENT_VIEW_STATE);
            FragmentTransaction ft = this.getFragmentManager().beginTransaction();
            if (isNeedHidden) {
                currentViewStateShow = false;
                ft.hide(this);
            } else {
                currentViewStateShow = true;
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
    protected boolean currentViewStateShow;

    private boolean isDestroyed = false;

    public abstract @LayoutRes
    int getContentView();

    public View getView() {
        return mView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        isDestroyed = false;
        int contentViewId = getContentView();
        if (contentViewId > 0) {
            mView = inflater.inflate(contentViewId, container, false);
        } else {
            mView = getContentLayout();
        }
        initViewPrevious();
        initView();
        initViewAfter();
        return mView;
    }

    protected void initViewPrevious() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isFirstShow = true;
        isFirstHidden = true;
        setIsCreateView(true);
        if (needLazyLoad()) {
            if (getUserVisibleHint() && isFirstLoadData()) {
                setIsFirstLoadData(false);
                initData();
            }
        } else {
            initData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isCreateView()) {
            if (isVisibleToUser) {
                checkViewShow();
            } else {
                checkViewHidden();
            }
        }

        if (needLazyLoad()) {
            if (isCreateView() && isVisibleToUser && isFirstLoadData()) {
                setIsFirstLoadData(false);
                initData();
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

    private boolean isFirstLoadData() {
        if (getArguments() == null) {
            return true;
        }
        return getArguments().getBoolean(FLAG_LAZY_IS_FIRST_LOAD, true);
    }

    private void setIsCreateView(boolean value) {
        if (getArguments() == null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(FLAG_LAZY_IS_DESTROY_VIEW, value);
            setArguments(bundle);
        } else {
            getArguments().putBoolean(FLAG_LAZY_IS_DESTROY_VIEW, value);
        }
    }

    private boolean isCreateView() {
        if (getArguments() == null) {
            return false;
        }
        return getArguments().getBoolean(FLAG_LAZY_IS_DESTROY_VIEW, false);
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

    public abstract void initViewAfter();

    public abstract void initData();


    public long getNoDoubleClickInterval() {
        return 900;
    }


    public abstract void onNoDoubleClick(View v);

    public void onViewClick(View v) {
    }

    public View getContentLayout() {
        TextView textView = new TextView(mActivity);
        textView.setText(this.getClass().getSimpleName() + ":getContentLayout()");
        return textView;
    }

    private void initAttr() {
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
        if (!isHidden() && getUserVisibleHint()) {
            checkViewShow();
        }
    }


    protected void onViewStateShow(boolean isFirstShow) {

    }

    protected void onViewStateHidden(boolean isFirstHidden) {

    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            checkViewHidden();
        } else {
            checkViewShow();
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
        checkViewHidden();
    }

    private void checkViewShow() {
        currentViewStateShow = true;
        this.onViewStateShow(isFirstShow);
        if (isFirstShow) {
            this.isFirstShow = false;
        }
    }

    private void checkViewHidden() {
        if (isFirstShow) {
            return;
        }
        if (!currentViewStateShow) {
            //隐藏之前判断之前是否显示，如果之前是隐藏，那么就不管
            return;
        }
        currentViewStateShow = false;
        onViewStateHidden(isFirstHidden);
        if (isFirstHidden) {
            isFirstHidden = false;
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
        } else if (mActivity.isFinishing()) {
            return true;
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            boolean destroyed = mActivity.isDestroyed();
            return destroyed;
        }
        return false;
    }

}
