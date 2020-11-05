package com.zr.wanandroid.module.my.presenter;

import com.github.load.Loading;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.my.activity.RegisterActivity;
import com.zr.wanandroid.module.my.model.RegisterModel;

public class RegisterPresenter extends BasePresenter<RegisterActivity> {
    public void register(String userName,String pwd,String rePwd){
        Loading.show(getView());
        RegisterModel.getInstance().register(userName, pwd, rePwd, new RequestListener() {
            @Override
            public void onSuccess(Object data) {
                Loading.dismissLoad();
                showToast("注册成功");
                getView().registerSuccess();
            }
            @Override
            public void onError(String code, String errorMsg) {
                Loading.dismissLoad();
                showToast(errorMsg);
            }
        });

    }
}
