package com.zr.wanandroid.module.my.presenter;

import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.manager.UserManager;
import com.zr.wanandroid.module.my.bean.CoinBean;
import com.zr.wanandroid.module.my.bean.CoinRecordBean;
import com.zr.wanandroid.module.my.fragment.MyFragment;
import com.zr.wanandroid.module.my.model.UserModel;

import java.util.List;

public class MyPresenter extends BasePresenter<MyFragment> {
    public void getCoin(){
        UserModel.getInstance().getUserCoin(new RequestListener<CoinBean>() {
            @Override
            public void onSuccess(CoinBean data) {
                UserManager.get().getUser().setCoinBean(data);
                UserManager.get().updateUser();
                getView().setCoin(data);
            }
            @Override
            public void onError(String code, String errorMsg) {
            }
        });
    }

    public void loginOut() {
        UserManager.get().clear();
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
