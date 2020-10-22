package com.zr.wanandroid.module.home.adapter;

import com.android.basecore.widget.adapter.CustomViewHolder;
import com.android.basecore.widget.adapter.LoadMoreAdapter;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;

public class HomeAdapter extends LoadMoreAdapter<HomeBannerBean> {
    public HomeAdapter() {
        super(R.layout.home_article_item, 15);
    }
    @Override
    public void bindData(CustomViewHolder holder, int position, HomeBannerBean item) {

    }
}
