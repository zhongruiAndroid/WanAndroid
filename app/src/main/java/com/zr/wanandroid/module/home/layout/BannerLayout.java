package com.zr.wanandroid.module.home.layout;

import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.banner.BannerHolder;
import com.github.banner.MyBannerView;
import com.github.banner.SimpleBannerItem;
import com.github.developtools.ActivityUtils;
import com.github.developtools.DensityUtils;
import com.github.developtools.ScreenUtils;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;
import com.zr.wanandroid.utils.IV;

import java.util.List;

public class BannerLayout extends MyBannerView {

    public BannerLayout(Context context) {
        super(context);
        initView();
    }
    public BannerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public BannerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        fullWidth=ScreenUtils.getScreenWidth(getContext());
        imageHeight=fullWidth*5/9;
    }
    private int fullWidth;
    private int imageHeight;
    public void setData(List<HomeBannerBean> list){
        setList(list);
        setIndicatorSelectDrawableColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        setIndicatorUnSelectDrawableColor(ContextCompat.getColor(getContext(),R.color.c_999999));
        addBannerItem(new SimpleBannerItem<HomeBannerBean>() {
            @Override
            public int getItemLayoutId() {
                return R.layout.home_banner_item_layout;
            }
            @Override
            public void bindData(BannerHolder holder, HomeBannerBean item, int position, int dataCount) {
                ImageView imageView = holder.getImageView(R.id.ivHomeBannerItem);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.width= fullWidth;
                layoutParams.height= imageHeight;
                imageView.setLayoutParams(layoutParams);
                IV.with(ActivityUtils.findActivity(getContext()),item.getImagePath(),imageView);
            }
            @Override
            public void onItemClick(HomeBannerBean item, int position, int dataCount) {
                super.onItemClick(item, position, dataCount);
            }
        });
        start();
    }
}
