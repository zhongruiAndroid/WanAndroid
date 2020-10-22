package com.zr.wanandroid.module.home.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.android.basecore.widget.adapter.CustomViewHolder;
import com.android.basecore.widget.adapter.LoadMoreAdapter;
import com.github.developtools.NumberUtils;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.home.bean.HomeBannerBean;

public class HomeAdapter extends LoadMoreAdapter<HomeArticleBean> {
    /*置顶文章数量*/
    private int topArticleSize;
    public HomeAdapter() {
        super(R.layout.home_article_item, 15);
    }
    @Override
    public void bindData(CustomViewHolder holder, int position, HomeArticleBean item) {
        if(isTopArticle(position)){
            //如果是置顶文章
        }else {

        }
        LinearLayout llHomeArticleTag = holder.getView(R.id.llHomeArticleTag);
        if(NumberUtils.isTrue(item.getFresh())){

        }

        tvHomeArticleTag
        tvHomeArticleTitle
                tvHomeArticleDes
        tvHomeArticleAuthor
                tvHomeArticleChapterName
        tvHomeArticleTime
    }
    private boolean isTopArticle(int position){
        return position<topArticleSize;
    }
    public void setTopListSize(int size) {
        topArticleSize=size;
    }
}
