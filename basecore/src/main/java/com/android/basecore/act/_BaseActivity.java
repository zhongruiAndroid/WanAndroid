package com.android.basecore.act;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.android.basecore.R;
import com.android.basecore.tools.ClickTools;

public abstract class _BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String USE_ANIM="USE_ANIM";
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
    private boolean useAnim=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent!=null){
            useAnim=intent.getBooleanExtra(USE_ANIM, true);
        }
        if(useAnim){
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else{
            overridePendingTransition(0, 0);
        }

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
    public void finish() {
        super.finish();
        hiddenKeyBoard(this,getWindow());
        if (useAnim) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            overridePendingTransition(0, 0);
        }
    }
    private void hiddenKeyBoard(Context context, Window window) {
        if(window==null||context==null){
            return;
        }
        View focusView=window.getCurrentFocus();
        if(focusView==null){
            return;
        }
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(window.getCurrentFocus().getWindowToken(), 0);
    }
    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
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
