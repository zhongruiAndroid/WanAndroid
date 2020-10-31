package com.zr.wanandroid.module.knowledgesystem.presenter;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.knowledgesystem.fragment.KnowledgeSystemArticleFragment;
import com.zr.wanandroid.module.knowledgesystem.model.KnowledgeSystemModel;

import java.util.List;

public class KnowledgeSystemArticleFragmentPresenter extends BasePresenter<KnowledgeSystemArticleFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private HomeAdapter adapter;
    private String id;

    public LoadMoreAdapter initAdapter() {
        adapter = new HomeAdapter(getView().getActivity());
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }

    public void getData(int page, String cid, boolean isLoad) {
        if (!isLoad) {
            id = cid;
        }
        KnowledgeSystemModel.getInstance().getKnowledgeSystemArticleList(page, cid, new BaseRequestListener<List<HomeArticleBean>, Boolean>() {
            @Override
            public void onSuccess(List<HomeArticleBean> obj) {
                loadResult(isLoad);
                if (!isLoad) {
                    adapter.setList(obj,true);
                    getView().showContent();
                }else{
                    adapter.addList(obj,true);
                }
            }
            @Override
            public void onCustomSuccess(List<HomeArticleBean> obj, Boolean hasMore) {
                adapter.loadHasMore(hasMore);
            }
            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
                loadError(adapter);
                if (!isLoad) {
                    getView().showError();
                }
            }
        });
    }

    @Override
    public void loadMore(LoadListener loadListener) {
        getData(pageNum, id, true);
    }
}
