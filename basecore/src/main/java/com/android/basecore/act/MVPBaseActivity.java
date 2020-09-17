package com.android.basecore.act;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.basecore.presenter._BasePresenter;
import com.android.basecore.presenter._BaseView;
import com.android.basecore.tools.AutoInstance;

public abstract class MVPBaseActivity<P extends _BasePresenter> extends _BaseActivity implements _BaseView {
    protected P mPresenter;
    protected P createPresenter() {
        return null;
    }
    public P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mPresenter == null) {
            mPresenter = AutoInstance.autoCreatePresenter(getClass());
        }
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter == null) {
            mPresenter = AutoInstance.autoCreatePresenter(getClass());
        }
        if (mPresenter != null) {
            mPresenter.setView(this);
            mPresenter.onCreate(savedInstanceState);
        }
        super.onCreate(savedInstanceState);


    }

    @Override
    public void showLoading() {

    }
    @Override
    public void dismissLoading() {

    }
    @Override
    public void toast(String content) {

    }
    @Override
    protected void onResume(boolean isFirstInto) {
        if (mPresenter != null) {
            mPresenter.onResume(isFirstInto);
        }
        super.onResume(isFirstInto);
    }

    @Override
    protected void onPause() {
        if (mPresenter != null) {
            mPresenter.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onStart() {
        if (mPresenter != null) {
            mPresenter.onStart();
        }
        super.onStart();
    }

    @Override
    protected void onRestart() {
        if (mPresenter != null) {
            mPresenter.onRestartForAct();
        }
        super.onRestart();
    }

    @Override
    protected void onStop(boolean isFirstHidden) {
        if (mPresenter != null) {
            mPresenter.onStopForAct(isFirstHidden);
        }
        super.onStop(isFirstHidden);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onAttachedToWindow() {
        if (mPresenter != null) {
            mPresenter.onAttachedToWindowForAct();
        }
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        if (mPresenter != null) {
            mPresenter.onDetachedFromWindowForAct();
        }
        super.onDetachedFromWindow();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mPresenter != null) {
            mPresenter.onConfigurationChanged(newConfig);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mPresenter != null) {
            mPresenter.onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (mPresenter != null) {
            mPresenter.onRestoreInstanceState(savedInstanceState);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (mPresenter != null) {
            mPresenter.onBackPressedForAct();
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (mPresenter != null) {
            mPresenter.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPresenter != null) {
            mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (mPresenter != null) {
            mPresenter.onNewIntentForAct(intent);
        }
        super.onNewIntent(intent);
    }

}
