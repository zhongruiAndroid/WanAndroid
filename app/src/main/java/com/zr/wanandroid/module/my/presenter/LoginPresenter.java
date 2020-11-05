package com.zr.wanandroid.module.my.presenter;

import com.github.load.Loading;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.my.activity.LoginActivity;
import com.zr.wanandroid.module.my.activity.RegisterActivity;
import com.zr.wanandroid.module.my.model.RegisterModel;

public class LoginPresenter extends BasePresenter<LoginActivity> {
    public void login(String userName,String pwd ){
        Loading.show(getView());
        RegisterModel.getInstance().login(userName, pwd, new RequestListener() {
            @Override
            public void onSuccess(Object data) {
                Loading.dismissLoad();
                showToast("登录成功");
                getView().loginSuccess();
            }
            @Override
            public void onError(String code, String errorMsg) {
                Loading.dismissLoad();
                showToast(errorMsg);
            }
        });

    }
}
