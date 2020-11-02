package com.zr.wanandroid.module.knowledgesystem.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.github.adapter.CustomAdapter;
import com.github.adapter.CustomViewHolder;
import com.github.adapter.listener.NoDoubleAdapterOnClickListener;
import com.github.interbus.InterBus;
import com.zr.wanandroid.R;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;
import com.zr.wanandroid.module.knowledgesystem.event.SelectKnowledgeEvent;

public class KnowledgeSystemAdapter extends CustomAdapter<KnowledgeSystemBean> {
    private int selectPosition;
    private boolean isKnowledgeSystem;
    public KnowledgeSystemAdapter(boolean isKnowledgeSystem) {
        super(R.layout.knowlegdge_system_item);
        this.isKnowledgeSystem=isKnowledgeSystem;
        setOnItemClickListener(new NoDoubleAdapterOnClickListener() {
            @Override
            public void onNoDoubleClick(View view, int position) {
                int prePosition=selectPosition;
                selectPosition=position;
                notifyItemChanged(prePosition);
                notifyItemChanged(selectPosition);
                InterBus.get().post(new SelectKnowledgeEvent(selectPosition,isKnowledgeSystem));
            }
        });
    }
    @Override
    public void bindData(CustomViewHolder holder, int i, KnowledgeSystemBean knowledgeSystemBean) {
        TextView tvKnowledgeClassifyTag = holder.getTextView(R.id.tvKnowledgeClassifyTag);
        TextView tvKnowledgeClassify = holder.getTextView(R.id.tvKnowledgeClassify);
        tvKnowledgeClassify.setText(knowledgeSystemBean.getName());
        if(holder.getAdapterPosition()==selectPosition){
            tvKnowledgeClassifyTag.setVisibility(View.VISIBLE);
            tvKnowledgeClassify.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),R.color.colorAccent));
        }else{
            tvKnowledgeClassifyTag.setVisibility(View.INVISIBLE);
            tvKnowledgeClassify.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),R.color.colorText));
        }
    }
    public void setSelectPosition(int itemPosition) {
        int prePosition=selectPosition;
        selectPosition=itemPosition;
        notifyItemChanged(prePosition);
        notifyItemChanged(selectPosition);
    }
}
