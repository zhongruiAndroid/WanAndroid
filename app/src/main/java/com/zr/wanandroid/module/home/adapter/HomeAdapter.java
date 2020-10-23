package com.zr.wanandroid.module.home.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.HtmlCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.basecore.widget.adapter.CustomViewHolder;
import com.android.basecore.widget.adapter.LoadMoreAdapter;
import com.github.developtools.DensityUtils;
import com.github.developtools.N;
import com.github.developtools.NumberUtils;
import com.github.fastshape.MyTextView;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.home.helper.ViewHelper;
import com.zr.wanandroid.utils.HtmlUtils;

import java.util.List;

public class HomeAdapter extends LoadMoreAdapter<HomeArticleBean> {
    /*置顶文章数量*/
    private int topArticleSize;

    public HomeAdapter() {
        super(R.layout.home_article_item, 20);
    }

    @Override
    public void bindData(CustomViewHolder holder, int position, HomeArticleBean item) {
        LinearLayout llHomeArticleTag = holder.getView(R.id.llHomeArticleTag);
        llHomeArticleTag.removeAllViews();
        if (isShowTag(item)) {
            if (NumberUtils.isTrue(item.getFresh())) {
                View newTagView = getTagView(llHomeArticleTag.getContext(),"新",ContextCompat.getColor(llHomeArticleTag.getContext(),R.color.c_home_tag_new));
                llHomeArticleTag.addView(newTagView);
            }
            if (isTopArticle(position)) {
                //如果是置顶文章
                View topTagView = getTagView(llHomeArticleTag.getContext(),"置顶",ContextCompat.getColor(llHomeArticleTag.getContext(),R.color.c_home_tag_border));
                llHomeArticleTag.addView(topTagView);
            }
            addTagView(llHomeArticleTag,item);


            ViewHelper.setVisibility(llHomeArticleTag, View.VISIBLE);
        } else {
            ViewHelper.setVisibility(llHomeArticleTag, View.GONE);
        }

        holder.setText(R.id.tvHomeArticleTitle, HtmlCompat.fromHtml("("+(1+position)+")"+item.getTitle(),HtmlCompat.FROM_HTML_MODE_LEGACY));
        TextView tvHomeArticleDes = holder.getTextView(R.id.tvHomeArticleDes);
        if(N.trimToEmptyNull(item.getDesc())){
            ViewHelper.setVisibility(tvHomeArticleDes,View.GONE);
        }else{
            tvHomeArticleDes.setText(HtmlCompat.fromHtml(item.getDesc(),HtmlCompat.FROM_HTML_MODE_LEGACY));
            ViewHelper.setVisibility(tvHomeArticleDes,View.VISIBLE);
        }
        holder.setText(R.id.tvHomeArticleAuthor,N.trimToEmptyNull(item.getAuthor())?item.getShareUser():item.getAuthor());
        String superChapterName="";
        if(!N.trimToEmptyNull(item.getSuperChapterName())){
            superChapterName=item.getSuperChapterName()+"·";
        }
        holder.setText(R.id.tvHomeArticleChapterName,superChapterName+item.getChapterName());
        holder.setText(R.id.tvHomeArticleTime,item.getNiceDate());

    }

    private boolean isTopArticle(int position) {
        return position < topArticleSize;
    }

    public void setTopListSize(int size) {
        topArticleSize = size;
    }

    public boolean isShowTag(HomeArticleBean item) {
        if (item == null) {
            return false;
        }
        if (NumberUtils.isTrue(item.getFresh())) {
            return true;
        }
        if (!N.isEmpty(item.getTags())) {
            return true;
        }
        return false;
    }

    public void addTagView(LinearLayout tagRoot,HomeArticleBean item) {
        if(tagRoot==null||item==null||N.isEmpty(item.getTags())){
            return;
        }
        Context ctx=tagRoot.getContext();
        for (HomeArticleBean.TagBean tag:item.getTags()){
            View tagView = getTagView(ctx, tag.getName(),ContextCompat.getColor(tagRoot.getContext(),R.color.c_999999));
            tagRoot.addView(tagView);
        }
    }
    public View getTagView(Context ctx,String tagText,int color) {
          /*   <com.github.fastshape.MyTextView
            android:id="@+id/tvHomeArticleTag"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginRight="7dp"
            android:paddingLeft="3dp"
            android:paddingTop="1dp"
            android:paddingRight="3dp"
            android:paddingBottom="1dp"
            android:text="新"
            android:textColor="@color/c_home_tag_new"
            android:textSize="11sp"
            app:borderColor="@color/c_home_tag_new"
            app:borderWidth="2px"
            app:radius="2dp" />*/

        int dp2 = DensityUtils.dp2px(2);
        MyTextView textView = new MyTextView(ctx);
        textView.setText(tagText);
        textView.setTextSize(11);
        textView.setTextColor(color);
        textView.getViewHelper().setBorderColor(color)
                .setBorderWidth(2).setRadius(dp2).complete();

        int dp3 = DensityUtils.dp2px(3);
        int dp1 = DensityUtils.dp2px(1);
        int dp7 = DensityUtils.dp2px(7);
        textView.setPadding(dp3, dp1, dp3, dp1);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.rightMargin = dp7;
        textView.setLayoutParams(params);
        return textView;
    }
}
