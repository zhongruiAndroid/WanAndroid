package com.zr.wanandroid.module.officialaccount.presenter;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.github.developtools.N;
import com.github.interbus.InterBus;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.officialaccount.bean.OfficialAccountBean;
import com.zr.wanandroid.module.officialaccount.fragment.OfficialAccountArticleFragment;
import com.zr.wanandroid.module.officialaccount.model.OfficialAccountModel;

import java.util.List;

public class OfficialAccountArticlePresenter extends BasePresenter<OfficialAccountArticleFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private HomeAdapter adapter;
    private String authorId;
    public HomeAdapter initAdapter(){
        adapter=new HomeAdapter(getView().getActivity());
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }
    public void getData(int page, boolean isLoad, String authorId) {
        this.authorId=authorId;
        OfficialAccountModel.getInstance().getOfficialArticleListByAuthor(page,authorId,new BaseRequestListener<List<HomeArticleBean>,Boolean>() {
            @Override
            public void onSuccess(List<HomeArticleBean> data) {
                loadResult(isLoad);
                if(N.isEmpty(data)){
                    getView().showEmpty();
                    return;
                }
                if(!isLoad){
                    adapter.setList(data,true);
                    getView().showContent();
                    getView().getArticleSuccess();
                }else{
                    adapter.addList(data,true);
                }
            }
            @Override
            public void onCustomSuccess(List<HomeArticleBean> obj, Boolean custom) {
                adapter.loadHasMore(custom);
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

    @Override
    public void loadMore(LoadListener loadListener) {
        getData(pageNum,true,authorId);
    }
}
