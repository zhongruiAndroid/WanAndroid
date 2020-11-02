package com.zr.wanandroid.module.knowledgesystem.presenter;

import com.github.developtools.N;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.knowledgesystem.adapter.KnowledgeSystemAdapter;
import com.zr.wanandroid.module.knowledgesystem.adapter.KnowledgeSystemSecondAdapter;
import com.zr.wanandroid.module.knowledgesystem.adapter.NavigationSecondAdapter;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;
import com.zr.wanandroid.module.knowledgesystem.fragment.NavigationFragment;
import com.zr.wanandroid.module.knowledgesystem.model.KnowledgeSystemModel;

import java.util.List;

public class NavigationPresenter extends BasePresenter<NavigationFragment> {
    private KnowledgeSystemAdapter adapter;
    private NavigationSecondAdapter adapterSecond;
    public KnowledgeSystemAdapter initAdapter() {
        adapter=new KnowledgeSystemAdapter(false);
        return adapter;
    }
    public NavigationSecondAdapter initSecondAdapter() {
        adapterSecond=new NavigationSecondAdapter(1);
        return adapterSecond;
    }
    public void getData(int page, boolean isLoad) {
        KnowledgeSystemModel.getInstance().getNavigationList(new RequestListener<List<KnowledgeSystemBean>>() {
            @Override
            public void onSuccess(List<KnowledgeSystemBean> obj) {
                loadResult(isLoad);
                if(N.isEmpty(obj)){
                    getView().showEmpty();
                }else{
                    adapter.setList(obj,true);
                    adapterSecond.setList(obj,true);
                    getView().showContent();
                }
            }
            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
                if(!isLoad){
                    getView().showError();
                }
            }
        });
    }
}
