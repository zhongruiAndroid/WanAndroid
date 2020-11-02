package com.zr.wanandroid.module.officialaccount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.adapter.CustomAdapter;
import com.github.adapter.CustomViewHolder;
import com.github.developtools.DensityUtils;
import com.github.developtools.ScreenUtils;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.officialaccount.bean.OfficialAccountBean;

public class OfficialAccountAdapter extends CustomAdapter<OfficialAccountBean> {
    private int itemWidth;
    public OfficialAccountAdapter(Context context) {
        super(R.layout.official_account_author_item);
        itemWidth=(ScreenUtils.getScreenWidth(context)-3*3)/3;
    }

    @Override
    public void bindData(CustomViewHolder holder, int i, OfficialAccountBean item) {
        TextView tvOfficialAccountAuthorName = holder.getTextView(R.id.tvOfficialAccountAuthorName);

        tvOfficialAccountAuthorName.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,itemWidth));
        tvOfficialAccountAuthorName.setText(item.getName());
    }
}
