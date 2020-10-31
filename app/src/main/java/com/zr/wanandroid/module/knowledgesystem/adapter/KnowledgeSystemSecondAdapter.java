package com.zr.wanandroid.module.knowledgesystem.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.android.basecore.listener.NoDoubleClickListener;
import com.github.adapter.CustomAdapter;
import com.github.adapter.CustomViewHolder;
import com.github.developtools.ActivityUtils;
import com.github.developtools.DensityUtils;
import com.github.developtools.N;
import com.github.fastshape.MyTextView;
import com.github.flowview.FlowLayout;
import com.github.interbus.InterBus;
import com.zr.wanandroid.R;
import com.zr.wanandroid.bridge.ActBridge;
import com.zr.wanandroid.module.home.event.SearchArticleEvent;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;

import java.util.List;

public class KnowledgeSystemSecondAdapter extends CustomAdapter<KnowledgeSystemBean> {
    public KnowledgeSystemSecondAdapter(int layoutId) {
        super(R.layout.knowlegdge_system_second_item);
    }

    @Override
    public void bindData(CustomViewHolder holder, int position, KnowledgeSystemBean item) {
        holder.setText(R.id.tvKnowledgeTitle, item.getName());
        List<KnowledgeSystemBean.ChildrenBean> children = item.getChildren();

        FlowLayout flKnowledge = holder.getView(R.id.flKnowledge);
        flKnowledge.removeAllViews();
        if (!N.isEmpty(children)) {
            for (KnowledgeSystemBean.ChildrenBean childrenBean : children) {
                View itemView = getItemView(holder.itemView.getContext(), childrenBean.getName(),item);
                flKnowledge.addView(itemView);
            }
        }
    }

    private View getItemView(Context context, String name,KnowledgeSystemBean item) {
        MyTextView textView = new MyTextView(context);
        int dp10 = DensityUtils.dp2px(10);
        int dp30 = DensityUtils.dp2px(30);
        textView.setTextSize(13);
        textView.setLayoutParams(new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp30));
        textView.setGravity(Gravity.CENTER);
        textView.setText(name);
        textView.setPadding(dp10, 0, dp10, 0);
        textView.getViewHelper().setRadius(DensityUtils.dp2px(15)).setSolidColor(ContextCompat.getColor(context, R.color.c_transparent_5)).complete();
        textView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View var1) {
                ActBridge.toKnowledgeSystemArticleActivity(ActivityUtils.findActivity(context),item);

            }
        });
        return textView;
    }
}
