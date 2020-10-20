package com.zr.wanandroid.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.basecore.listener.NoDoubleClickListener;
import com.github.developtools.ActivityUtils;
import com.zr.wanandroid.BuildConfig;
import com.zr.wanandroid.R;

public class TitleView extends RelativeLayout {
    public TitleView(Context context) {
        super(context);
        init();
    }
    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_title, this);
        initView();
    }
    /**************************************************************************/
    protected RelativeLayout appRlTitle;
    protected TextView appTvTitle;
    protected TextView appTvRightTitle;

    protected ImageButton appIbBack;
    protected ImageButton appIbRight;

    protected View appBottomLine;

    private String appTitle, appRightTitle;
    private int appTitleColor, appRightTitleColor;
    private Drawable appBackIcon;
    private Drawable appRightImg;

    private int appTitleBackground = R.color.app_bar;

    private boolean hiddenBottomLine = false;
    private void initView() {
        appRlTitle=this;
        appBackIcon=ContextCompat.getDrawable(getContext(),R.drawable.app_back_black);
        this.appIbBack =findViewById(R.id.appIbBack);
        StateListDrawable stateListDrawable=new StateListDrawable();
        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed},new ColorDrawable(Color.TRANSPARENT));
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor("#e2e2e2")));
        stateListDrawable.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        this.appIbBack.setBackground(stateListDrawable);
        setAppBackIcon(appBackIcon);
        this.appIbBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View var1) {
                Activity activity = ActivityUtils.findActivity(getContext());
                if (activity!= null) {
                    activity.finish();
                }
            }
        });
    }
    public void hiddenBottomLine() {
        hiddenBottomLine = true;
        if (appBottomLine == null) {
            appBottomLine=findViewById(R.id.appBottomLine);
        }
        appBottomLine.setVisibility(View.GONE);
    }
    public void setAppBackIcon(Drawable appBackIcon) {
        this.appBackIcon = appBackIcon;
        if(appIbBack==null){
            appIbBack=findViewById(R.id.appIbBack);
        }
        if(this.appBackIcon==null){
            this.appIbBack.setVisibility(View.INVISIBLE);
        }else{
            if(this.appIbBack.getVisibility()!=View.VISIBLE){
                this.appIbBack.setVisibility(View.VISIBLE);
            }
            this.appIbBack.setImageDrawable(this.appBackIcon);
        }
    }
    public void setAppBackIcon(@DrawableRes int appBackIconResId) {
        setAppBackIcon(ContextCompat.getDrawable(getContext(),appBackIconResId));
    }
    protected void setAppTitle(String title, boolean isShort, int textLength) {
        if (isShort && !TextUtils.isEmpty(title) && title.length() > textLength) {
            appTitle = title.substring(0, textLength) + "...";
        } else {
            appTitle = title;
        }
        if(appTvTitle==null){
            appTvTitle=findViewById(R.id.appTvTitle);
        }
        if (BuildConfig.DEBUG) {
            appTvTitle.setText(appTitle == null ? "" : appTitle + "(DeBug)");
        } else {
            appTvTitle.setText(appTitle == null ? "" : appTitle);
        }
    }
    protected void setAppTitle(String title, boolean isShort) {
        setAppTitle(title, isShort, 9);
    }
    protected void setAppTitle(String title) {
        setAppTitle(title, true, 9);
    }

    public void setAppRightTitle(String appRightTitle) {
        this.appRightTitle = appRightTitle;
        if (appTvRightTitle == null) {
            appTvRightTitle=findViewById(R.id.appTvRightTitle);
        }
        appTvRightTitle.setText(appRightTitle == null ? "" : appRightTitle);
    }
    public void setAppRightImg(Drawable appRightImg,OnClickListener onClickListener) {
        if (appTvRightTitle == null) {
            appTvRightTitle=findViewById(R.id.appTvRightTitle);
        }
        if(appIbRight==null){
            appIbRight=findViewById(R.id.appIbRight);
        }
        this.appRightImg = appRightImg;
        if(appRightImg==null){
            appIbRight.setVisibility(View.INVISIBLE);
        }else{
            appIbRight.setImageDrawable(appRightImg);
            appTvRightTitle.setVisibility(View.INVISIBLE);
            appIbRight.setVisibility(View.VISIBLE);
            appIbRight.setOnClickListener(onClickListener);
        }
    }
    public void setAppRightImg(@DrawableRes int appRightImgResId,OnClickListener onClickListener) {
        setAppRightImg(ContextCompat.getDrawable(getContext(),appRightImgResId),onClickListener);
    }

    public void setAppTitleBackground(@ColorRes int titleBackground) {
        this.appTitleBackground = titleBackground;
        if (appRlTitle != null&&this.appTitleBackground!=0) {
            appRlTitle.setBackgroundColor(ContextCompat.getColor(getContext(), appTitleBackground));
        }
    }
    public void setAppTitleColor(@ColorRes int appTitleColor) {
        if(appTvTitle==null){
            appTvTitle=findViewById(R.id.appTvTitle);
        }
        this.appTitleColor = appTitleColor;
        if(this.appTitleColor!=0){
            this.appTvTitle.setTextColor(ContextCompat.getColor(getContext(),this.appTitleColor));
        }
    }

    public void setAppRightTitleColor(@ColorRes int appRightTitleColor) {
        if (appTvRightTitle == null) {
            appTvRightTitle=findViewById(R.id.appTvRightTitle);
        }
        this.appRightTitleColor = appRightTitleColor;
        if(this.appRightTitleColor!=0){
            this.appTvRightTitle.setTextColor(ContextCompat.getColor(getContext(),this.appRightTitleColor));
        }
    }
}
