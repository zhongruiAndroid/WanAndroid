package com.zr.wanandroid.module.officialaccount.adapter;

import com.github.adapter.CustomAdapter;
import com.github.adapter.CustomViewHolder;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.officialaccount.bean.OfficialAccountBean;

public class OfficialAccountAdapter extends CustomAdapter<OfficialAccountBean> {
    public OfficialAccountAdapter(int layoutId) {
        super(R.layout.official_account_item);
    }

    @Override
    public void bindData(CustomViewHolder holder, int i, OfficialAccountBean item) {
        holder.setText(R.id.tvOfficialAccountAuthorName, item.getName());
    }
}
