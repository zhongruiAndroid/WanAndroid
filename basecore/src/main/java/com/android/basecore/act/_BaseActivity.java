package com.android.basecore.act;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.basecore.tools.ClickTools;

public abstract class _BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Long clickTimeFlag;
    protected FragmentActivity mActivity;
    protected boolean isFirstInto = true;
    protected boolean isFirstHidden = true;
    private boolean isDestroyed=false;

    public @StyleRes
    int getThemeId() {
        return 0;
    }


    public abstract @LayoutRes
    int getContentView();

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
        TextView textView = new TextView(this);
        textView.setText(this.getClass().getSimpleName()+":getContentLayout()");
        return textView;
    }

    private void initAttr() {
        isDestroyed=false;
        isFirstInto=true;
        isFirstHidden=true;
        mActivity = this;
        clickTimeFlag = new Long(hashCode());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAttr();
        _setTheme(getThemeId());
        int contentViewId = getContentView();
        if (contentViewId > 0) {
            setContentView(contentViewId);
        } else {
            setContentView(getContentLayout());
        }

        initView();
        initViewAfter();
        initData();
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ClickTools.get().removeLastClickTime(clickTimeFlag);
    }

    protected void _setTheme(int themeId) {
        if (themeId <= 0) {
            return;
        }
        if(isDestroyed){
            return;
        }
        setTheme(themeId);
    }

    @Override
    protected final void onResume() {
        super.onResume();
        isDestroyed=false;
        onResume(isFirstInto);
        if (isFirstInto) {
            this.isFirstInto = false;
        }
    }

    @Override
    protected final void onStop() {
        super.onStop();
        onStop(isFirstHidden);
        if (isFirstHidden) {
            this.isFirstHidden = false;
        }
    }

    protected void onResume(boolean isFirstInto) {

    }
    protected void onStop(boolean isFirstHidden) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        ClickTools.get().clearLastClickTime(clickTimeFlag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClickTools.get().removeLastClickTime(clickTimeFlag);
        isDestroyed=true;
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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return isDestroyed();
        }else{
            return isFinishing()||isDestroyed;
        }
    }

    public void actFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
