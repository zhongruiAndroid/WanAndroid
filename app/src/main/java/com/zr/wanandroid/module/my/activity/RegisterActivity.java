package com.zr.wanandroid.module.my.activity;

import android.text.TextUtils;
import android.view.View;

import com.github.developtools.N;
import com.github.fastshape.MyEditText;
import com.github.fastshape.MyTextView;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.module.my.presenter.RegisterPresenter;

public class RegisterActivity extends BaseActivity<RegisterPresenter> {
    private MyEditText etRegisterUserName;
    private MyEditText etRegisterPwd;
    private MyEditText etRegisterRePwd;
    private MyTextView tvRegister;

    @Override
    public int getContentView() {
        return R.layout.register_act;
    }

    @Override
    public void initView() {
        titleView.setAppTitle("注册");
        etRegisterUserName = findViewById(R.id.etRegisterUserName);
        etRegisterPwd = findViewById(R.id.etRegisterPwd);
        etRegisterRePwd = findViewById(R.id.etRegisterRePwd);
        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.tvRegister:
                register();
            break;
        }
    }

    private void register() {
        String userName = etRegisterUserName.getText().toString();
        String pwd = etRegisterPwd.getText().toString();
        String rePwd = etRegisterRePwd.getText().toString();
        if(N.isEmpty(userName)){
            showToast("请输入用户名");
            return;
        }
        if(N.isEmpty(pwd)){
            showToast("请输入密码");
            return;
        }
        if(!TextUtils.equals(pwd,rePwd)){
            showToast("两次密码不一样,请重新输入");
            return;
        }
        getPresenter().register(userName,pwd,rePwd);
    }
    public void registerSuccess() {
        finish();
    }
}
