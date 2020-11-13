package com.zr.wanandroid.module.my.adapter;

import com.github.adapter.CustomViewHolder;
import com.github.adapter.LoadMoreAdapter;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.my.bean.CoinRecordBean;

public class CoinRecordAdapter extends LoadMoreAdapter<CoinRecordBean> {
    public CoinRecordAdapter(int layoutId) {
        super(R.layout.coin_record_item);
    }
    @Override
    public void bindData(CustomViewHolder holder, int i, CoinRecordBean item) {
        holder.setText(R.id.tvCoinTitle,item.getReason());
        holder.setText(R.id.tvCoinDes,item.getDesc());
        holder.setText(R.id.tvCoinCount,"+"+item.getCoinCount());
    }
}
