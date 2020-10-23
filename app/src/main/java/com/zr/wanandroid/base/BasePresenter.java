package com.zr.wanandroid.base;

import com.android.basecore.presenter._BasePresenter;
import com.android.basecore.presenter._BaseView;
import com.android.basecore.widget.adapter.LoadMoreAdapter;
import com.github.developtools.StringUtils;
import com.zr.wanandroid.utils.ToastUtils;

public class BasePresenter<V extends BaseView> extends _BasePresenter<V>  {
    public int pageNum=2;
    @Override
    public void onDestroy() {
        ToastUtils.cancelToast();
        super.onDestroy();
    }
    public void showToast(String text){
        ToastUtils.showToast(text);
    }
    public void loadResult(boolean isLoad) {
        getView().refreshComplete();
        if(isLoad){
            pageNum+=1;
        }else{
            pageNum=2;
        }
    }
    public void loadError(LoadMoreAdapter adapter) {
        getView().refreshComplete();
        if(adapter==null){
            return;
        }
        adapter.setStatus(LoadMoreAdapter.error);
    }
}
