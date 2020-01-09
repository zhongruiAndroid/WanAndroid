package com.android.basecore.act;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.basecore.R;
import com.android.basecore.tools.ClickTools;

public abstract class _BaseActivity extends AppCompatActivity  implements View.OnClickListener {
    private Long clickTimeFlag;
    protected Activity mActivity;

    public abstract @LayoutRes
    int getContentView();

    public abstract void initView();

    public abstract void initData();

    public abstract void setViewListener();
    public long getNoDoubleClickInterval(){
        return 900;
    };
    public abstract void onNoDoubleClick(View view);

    public void onViewClick(View view){};

    public View getContentLayout() {
        return null;
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        clickTimeFlag=new Long(hashCode());
        int contentViewId = getContentView();
        if (contentViewId > 0) {
            setContentView(contentViewId);
        } else {
            setContentView(getContentLayout());
        }

        initView();
        setViewListener();
        initData();
    }

    @Override
    public final void onClick(View v) {
        onViewClick(v);
        boolean fastClick = ClickTools.get().isFastClick(clickTimeFlag, v,getNoDoubleClickInterval());
        if(!fastClick){
            onNoDoubleClick(v);
        }
    }
}
