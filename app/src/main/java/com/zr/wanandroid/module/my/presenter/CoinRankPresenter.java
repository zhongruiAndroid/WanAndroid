package com.zr.wanandroid.module.my.presenter;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.module.my.activity.CoinRankActivity;
import com.zr.wanandroid.module.my.activity.CoinRecordActivity;
import com.zr.wanandroid.module.my.adapter.CoinRankAdapter;
import com.zr.wanandroid.module.my.adapter.CoinRecordAdapter;
import com.zr.wanandroid.module.my.bean.CoinBean;
import com.zr.wanandroid.module.my.bean.CoinRecordBean;
import com.zr.wanandroid.module.my.model.UserModel;

import java.util.List;

public class CoinRankPresenter extends BasePresenter<CoinRankActivity> implements LoadMoreAdapter.OnLoadMoreListener {
    private CoinRankAdapter adapter;
    public LoadMoreAdapter initAdapter(){
        adapter=new CoinRankAdapter(1);
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }

    public void getCoinRank(int page,boolean isLoad){
        UserModel.getInstance().getCoinRank(page,new BaseRequestListener<List<CoinBean>,Boolean>() {
            @Override
            public void onSuccess(List<CoinBean> data) {
                loadResult(isLoad);
                if(!isLoad){
                    adapter.setList(data,true);
                    getView().showContent();
                }else{
                    adapter.addList(data,true);
                }
            }
            @Override
            public void onCustomSuccess(List<CoinBean> data, Boolean custom) {
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
        getCoinRank(pageNum,true);
    }

}
