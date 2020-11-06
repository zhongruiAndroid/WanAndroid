package com.zr.wanandroid.module.my.presenter;

import com.github.developtools.SPUtils;
import com.github.interbus.InterBus;
import com.github.load.Loading;
import com.zr.wanandroid.AppXml;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.manager.UserManager;
import com.zr.wanandroid.module.my.activity.RegisterActivity;
import com.zr.wanandroid.module.my.event.LoginSuccessEvent;
import com.zr.wanandroid.module.my.model.UserModel;

public class RegisterPresenter extends BasePresenter<RegisterActivity> {
    public void register(String userName,String pwd,String rePwd){
        Loading.show(getView());
        UserModel.getInstance().register(userName, pwd, rePwd, new RequestListener<String>() {
            @Override
            public void onSuccess(String data) {
                SPUtils.setPrefString(AppXml.USER_KEY,data);
                Loading.dismissLoad();
                showToast("注册成功");
                InterBus.get().post(new LoginSuccessEvent(UserManager.get().getUser()));
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
