package com.zr.wanandroid.module.project.adapter;

import android.support.v4.text.HtmlCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.adapter.CustomViewHolder;
import com.github.adapter.LoadMoreAdapter;
import com.github.developtools.ActivityUtils;
import com.github.developtools.N;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.home.helper.ViewHelper;
import com.zr.wanandroid.utils.IV;

public class ProjectListAdapter extends LoadMoreAdapter<HomeArticleBean> {
    public ProjectListAdapter(int layoutId) {
        super(R.layout.project_list_item);
    }
    @Override
    public void bindData(CustomViewHolder holder, int position, HomeArticleBean item) {
        ImageView imageView = holder.getImageView(R.id.ivProjectImage);
        IV.with(ActivityUtils.findActivity(holder.itemView.getContext()),item.getEnvelopePic(),imageView);

        holder.setText(R.id.tvProjectTitle, HtmlCompat.fromHtml("("+(1+position)+")"+item.getTitle(),HtmlCompat.FROM_HTML_MODE_LEGACY));
        TextView tvProjectDes = holder.getTextView(R.id.tvProjectDes);
        if(N.trimToEmptyNull(item.getDesc())){
            ViewHelper.setVisibility(tvProjectDes, View.GONE);
        }else{
            tvProjectDes.setText(HtmlCompat.fromHtml(item.getDesc(),HtmlCompat.FROM_HTML_MODE_LEGACY));
            ViewHelper.setVisibility(tvProjectDes,View.VISIBLE);
        }
        holder.setText(R.id.tvProjectAuthor, N.trimToEmptyNull(item.getAuthor())?item.getShareUser():item.getAuthor());
        holder.setText(R.id.tvProjectTime,item.getNiceDate());
    }
}
