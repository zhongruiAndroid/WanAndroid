package com.zr.wanandroid.module.my.presenter;

import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.my.fragment.MyFragment;
import com.zr.wanandroid.module.my.model.UserModel;

public class MyPresenter extends BasePresenter<MyFragment> {
    public void getCoin(){
        UserModel.getInstance().getUserCoin(new RequestListener<String>() {
            @Override
            public void onSuccess(String data) {

            }
            @Override
            public void onError(String code, String errorMsg) {

            }
        });
    }

    public void loginOut() {
        UserModel.getInstance().loginOut(new RequestListener<String>() {
            @Override
            public void onSuccess(String data) {

            }
            @Override
            public void onError(String code, String errorMsg) {

            }
        });
    }
}
