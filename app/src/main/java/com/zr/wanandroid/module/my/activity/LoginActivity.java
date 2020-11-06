package com.zr.wanandroid.module.my.activity;

import android.graphics.Color;
import android.view.View;

import com.android.basecore.listener.NoDoubleClickListener;
import com.github.developtools.N;
import com.github.fastshape.MyEditText;
import com.github.fastshape.MyTextView;
import com.github.interbus.BusCallback;
import com.zr.wanandroid.BuildConfig;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.bridge.ActBridge;
import com.zr.wanandroid.module.my.event.LoginSuccessEvent;
import com.zr.wanandroid.module.my.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> {
    private MyEditText etLoginUserName;
    private MyEditText etLoginPwd;
    private MyTextView tvLogin;

    @Override
    public int getContentView() {
        return R.layout.login_act;
    }

    @Override
    public void initView() {
        titleView.setAppTitle("登录");
        titleView.setAppRightTitle("去注册");
        titleView.setAppRightTitleColor(Color.WHITE);
        titleView.setAppRightTitleOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View var1) {
                ActBridge.toRegisterActivity(mActivity);
            }
        });
        if(BuildConfig.DEBUG){
            titleView.getAppTitleView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View var1) {
                    etLoginUserName.setText("17621060271");
                    etLoginPwd.setText("wanlxqwe123");
                }
            });
        }
        etLoginUserName = findViewById(R.id.etLoginUserName);
        etLoginPwd = findViewById(R.id.etLoginPwd);
        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void initBus() {
        setEvent(LoginSuccessEvent.class, new BusCallback<LoginSuccessEvent>() {
            @Override
            public void accept(LoginSuccessEvent event) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.tvLogin:
                login();
            break;
        }
    }

    private void login() {
        String userName=etLoginUserName.getText().toString();
        String pwd = etLoginPwd.getText().toString();
        if(N.trimToEmptyNull(userName)){
            showToast("请输入用户名");
            return;
        }
        if(N.trimToEmptyNull(pwd)){
            showToast("请输入密码");
            return;
        }
        getPresenter().login(userName,pwd);
    }
    public void loginSuccess(){

    }
}
