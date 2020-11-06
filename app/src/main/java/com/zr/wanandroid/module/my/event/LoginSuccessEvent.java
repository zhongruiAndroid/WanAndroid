package com.zr.wanandroid.module.my.event;

import com.github.interbus.InterBus;
import com.zr.wanandroid.module.my.bean.UserBean;

public class LoginSuccessEvent {
    public UserBean userBean;
    public LoginSuccessEvent(UserBean userBean) {
        this.userBean = userBean;
    }
}
