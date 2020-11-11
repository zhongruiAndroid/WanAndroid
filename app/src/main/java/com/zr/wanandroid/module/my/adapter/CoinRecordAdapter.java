package com.zr.wanandroid.module.my.adapter;

import com.github.adapter.CustomViewHolder;
import com.github.adapter.LoadMoreAdapter;
import com.zr.wanandroid.module.my.bean.CoinRecordBean;

public class CoinRecordAdapter extends LoadMoreAdapter<CoinRecordBean> {
    public CoinRecordAdapter(int layoutId) {
        super(layoutId);
    }
    @Override
    public void bindData(CustomViewHolder customViewHolder, int i, CoinRecordBean coinRecordBean) {

    }
}
