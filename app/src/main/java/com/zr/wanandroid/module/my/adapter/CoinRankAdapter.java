package com.zr.wanandroid.module.my.adapter;

import com.github.adapter.CustomViewHolder;
import com.github.adapter.LoadMoreAdapter;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.my.bean.CoinBean;
import com.zr.wanandroid.module.my.bean.CoinRecordBean;

public class CoinRankAdapter extends LoadMoreAdapter<CoinBean> {
    public CoinRankAdapter(int layoutId) {
        super(R.layout.coin_rank_item);
    }
    @Override
    public void bindData(CustomViewHolder holder, int i, CoinBean item) {
        holder.setText(R.id.tvCoinRankNum,item.getRank());
        holder.setText(R.id.tvCoinRankName,item.getUsername());
        holder.setText(R.id.tvCoinRankLevel,"等级:"+item.getLevel());
        holder.setText(R.id.tvCoinRankCount,"积分:"+item.getCoinCount());
    }
}
