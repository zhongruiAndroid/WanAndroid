package com.zr.wanandroid.module.officialaccount.presenter;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.github.developtools.N;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.officialaccount.activity.OfficialAccountArticleActivity;
import com.zr.wanandroid.module.officialaccount.model.OfficialAccountModel;

import java.util.List;

public class OfficialAccountSearchPresenter extends BasePresenter<OfficialAccountArticleActivity> implements LoadMoreAdapter.OnLoadMoreListener {
    private HomeAdapter adapter;
    private String authorId;
    private String searchText;
    public HomeAdapter initAdapter(){
        adapter=new HomeAdapter(getView() );
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }
    public void getData(int page, boolean isLoad, String authorId,String searchText) {
        this.authorId=authorId;
        this.searchText=searchText;
        OfficialAccountModel.getInstance().getOfficialArticleListByAuthor(page,authorId,searchText,new BaseRequestListener<List<HomeArticleBean>,Boolean>() {
            @Override
            public void onSuccess(List<HomeArticleBean> data) {
                loadResult(isLoad);
                if(N.isEmpty(data)){
                    adapter.setList(data,true);
                    getView().showEmpty();
                    return;
                }
                if(!isLoad){
                    adapter.setList(data,true);
                    getView().showContent();
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
                if(isLoad){
                    loadError(adapter);
                }else{
                    getView().showError();
                }
            }
        });
    }
    @Override
    public void loadMore(LoadListener loadListener) {
        getData(pageNum,true,authorId,searchText);
    }
}
