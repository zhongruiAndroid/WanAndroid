package com.zr.wanandroid.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
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

    private boolean useCustomTitleView;

    public TitleView(Context context) {
        super(context);
        init(null);
    }
    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleView);
        useCustomTitleView = typedArray.getBoolean(R.styleable.TitleView_useCustomTitleView, false);
        typedArray.recycle();
        if(useCustomTitleView){

        }else{
            LayoutInflater.from(getContext()).inflate(R.layout.layout_title, this);
            initView();
        }
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

    private int appTitleBackground ;

    private boolean hiddenBottomLine = false;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (useCustomTitleView) {
            initView();
        }
    }

    private void initView() {
        appTitleBackground=ContextCompat.getColor(getContext(),R.color.app_bar);
        appRlTitle=this;
        appBackIcon=ContextCompat.getDrawable(getContext(),R.drawable.app_back_black);
        this.appIbBack =findViewById(R.id.appIbBack);
        StateListDrawable stateListDrawable=new StateListDrawable();
        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed},new ColorDrawable(Color.TRANSPARENT));
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor("#e2e2e2")));
        stateListDrawable.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
//        this.appIbBack.setBackground(stateListDrawable);
        appBackIcon.mutate().setColorFilter(ContextCompat.getColor(getContext(),R.color.app_bar), PorterDuff.Mode.SRC_IN);
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


        setAppTitleBackground(ContextCompat.getColor(getContext(),R.color.colorAccent));
        setAppTitleColor(ContextCompat.getColor(getContext(),R.color.c_white));

    }
    public void setAppBackClickListener(OnClickListener listener){
        if (this.appIbBack == null) {
            return;
        }
        this.appIbBack.setOnClickListener(listener);
    }
    public void hiddenBottomLine() {
        hiddenBottomLine = true;
        if (appBottomLine == null) {
            appBottomLine=findViewById(R.id.appBottomLine);
        }
        appBottomLine.setVisibility(View.GONE);
    }
    public void setAppBackIconColor(@ColorInt int color) {
        if(appBackIcon==null){
            return;
        }
        appBackIcon=appBackIcon.mutate();
        appBackIcon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        setAppBackIcon(appBackIcon);

    }
    public void setAppBackIcon(Drawable appBackIcon) {
        this.appBackIcon = appBackIcon;
        if(appIbBack==null){
            appIbBack=findViewById(R.id.appIbBack);
        }
        if(this.appBackIcon==null){
            this.appIbBack.setVisibility(View.INVISIBLE);
        }else if(appIbBack!=null){
            if(this.appIbBack.getVisibility()!=View.VISIBLE){
                this.appIbBack.setVisibility(View.VISIBLE);
            }
            this.appIbBack.setImageDrawable(this.appBackIcon);
        }
    }
    public void setAppBackIcon(@DrawableRes int appBackIconResId) {
        setAppBackIcon(ContextCompat.getDrawable(getContext(),appBackIconResId));
    }
    public void setAppTitle(String title, boolean isShort, int textLength) {
        if (isShort && !TextUtils.isEmpty(title) && title.length() > textLength) {
            appTitle = title.substring(0, textLength) + "...";
        } else {
            appTitle = title;
        }
        if(appTvTitle==null){
            appTvTitle=findViewById(R.id.appTvTitle);
        }
        if(appTvTitle==null){
            return;
        }
        if (BuildConfig.DEBUG) {
            appTvTitle.setText(appTitle == null ? "" : appTitle + "(DeBug)");
        } else {
            appTvTitle.setText(appTitle == null ? "" : appTitle);
        }
    }
    public void setAppTitle(String title, boolean isShort) {
        setAppTitle(title, isShort, 9);
    }
    public void setAppTitle(String title) {
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
        }else if(appIbRight!=null){
            appIbRight.setImageDrawable(appRightImg);
            if(appTvRightTitle!=null){
                appTvRightTitle.setVisibility(View.INVISIBLE);
            }
            appIbRight.setVisibility(View.VISIBLE);
            appIbRight.setOnClickListener(onClickListener);
        }
    }
    public void setAppRightImg(@DrawableRes int appRightImgResId,OnClickListener onClickListener) {
        setAppRightImg(ContextCompat.getDrawable(getContext(),appRightImgResId),onClickListener);
    }

    public void setAppTitleBackground(@ColorInt int titleBackground) {
        this.appTitleBackground = titleBackground;
        if (appRlTitle != null&&this.appTitleBackground!=0) {
            appRlTitle.setBackgroundColor(appTitleBackground);
        }
    }
    public void setAppTitleColor(@ColorInt int appTitleColor) {
        if(appTvTitle==null){
            appTvTitle=findViewById(R.id.appTvTitle);
        }
        this.appTitleColor = appTitleColor;
        if(this.appTitleColor!=0&&appTvTitle!=null){
            this.appTvTitle.setTextColor(this.appTitleColor);
        }
    }

    public void setAppRightTitleColor(@ColorInt int appRightTitleColor) {
        if (appTvRightTitle == null) {
            appTvRightTitle=findViewById(R.id.appTvRightTitle);
        }
        this.appRightTitleColor = appRightTitleColor;
        if(this.appRightTitleColor!=0&&appTvRightTitle!=null){
            this.appTvRightTitle.setTextColor(appRightTitleColor);
        }
    }

//    private OnClickListener rightTitleOnClickListener;
    public void setAppRightTitleOnClickListener(OnClickListener onClickListener) {
//        rightTitleOnClickListener=onClickListener;
        if (appTvRightTitle != null) {
            appTvRightTitle.setOnClickListener(onClickListener);
        }

    }
}
