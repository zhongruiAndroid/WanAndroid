package com.zr.wanandroid.module.my.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.fastshape.MyImageView;
import com.github.fastshape.MyTextView;
import com.github.interbus.BusCallback;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.bridge.ActBridge;
import com.zr.wanandroid.common.manager.UserManager;
import com.zr.wanandroid.module.home.helper.ViewHelper;
import com.zr.wanandroid.module.my.bean.CoinBean;
import com.zr.wanandroid.module.my.bean.UserBean;
import com.zr.wanandroid.module.my.event.LoginOutEvent;
import com.zr.wanandroid.module.my.event.LoginSuccessEvent;
import com.zr.wanandroid.module.my.presenter.MyPresenter;

public class MyFragment extends BaseFragment<MyPresenter> {
    private ImageView ivMyRanking;
    private MyImageView ivMyHead;
    private TextView tvMyName;
    private TextView tvMyLevel;
    private MyTextView tvMyJiFen;
    private TextView tvMyCoin;
    private MyTextView tvMyCollect;
    private MyTextView tvMyShare;
    private MyTextView tvMyTodo;
    private MyTextView tvMyNight;
    private MyTextView tvMySetting;
    private TextView tvMyExit;

    @Override
    public int getContentView() {
        return R.layout.my_frag;
    }

    @Override
    public void initView() {

        ivMyRanking = findViewById(R.id.ivMyRanking, true);
        ivMyHead = findViewById(R.id.ivMyHead, true);
        tvMyName = findViewById(R.id.tvMyName, true);
        tvMyLevel = findViewById(R.id.tvMyLevel, true);
        tvMyJiFen = findViewById(R.id.tvMyJiFen, true);
        tvMyCoin = findViewById(R.id.tvMyCoin);
        tvMyCollect = findViewById(R.id.tvMyCollect, true);
        tvMyShare = findViewById(R.id.tvMyShare, true);
        tvMyTodo = findViewById(R.id.tvMyTodo, true);
        tvMyNight = findViewById(R.id.tvMyNight, true);
        tvMySetting = findViewById(R.id.tvMySetting, true);
        tvMyExit = findViewById(R.id.tvMyExit, true);


    }

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initBus() {
        setEvent(LoginSuccessEvent.class, new BusCallback<LoginSuccessEvent>() {
            @Override
            public void accept(LoginSuccessEvent event) {
                getPresenter().getCoin();
                setViewByUser();
            }
        });
        setEvent(LoginOutEvent.class, new BusCallback<LoginOutEvent>() {
            @Override
            public void accept(LoginOutEvent event) {
                setViewByUser();
            }
        });
    }

    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {
        setViewByUser();
    }

    private void setViewByUser() {
        UserBean user = UserManager.get().getUser();
        if (user == null) {
            tvMyName.setText("去登录");
            tvMyCoin.setText("");
            tvMyLevel.setText("等级:-- 排名:--");
            ViewHelper.setVisibility(tvMyExit,View.GONE);
        } else {
            CoinBean coinBean = user.getCoinBean();
            ViewHelper.setVisibility(tvMyExit,View.VISIBLE);
            tvMyName.setText(user.getNickname());
            tvMyCoin.setText(coinBean.getCoinCount());
            String level = coinBean.getLevel();
            String rank = coinBean.getRank();
            tvMyLevel.setText("等级:"+level+" 排名:"+rank);
        }
    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()) {
            case R.id.ivMyHead:
            case R.id.tvMyName:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }

                break;
            case R.id.ivMyRanking:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }
                getPresenter().getUserRank(1);
                break;
            case R.id.tvMyLevel:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }
                break;
            case R.id.tvMyJiFen:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }
                break;
            case R.id.tvMyCollect:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }
                break;
            case R.id.tvMyShare:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }
                break;
            case R.id.tvMyTodo:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }
                break;
            case R.id.tvMyNight:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }
                break;
            case R.id.tvMySetting:
                if(UserManager.noLogin()){
                    ActBridge.toLoginActivity(mActivity);
                    return;
                }
                break;
            case R.id.tvMyExit:
                loginOut();
                break;
        }
    }

    private void loginOut() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage("是否退出登录?");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getPresenter().loginOut();
                setViewByUser();
                ViewHelper.setVisibility(tvMyExit,View.GONE);
            }
        });
        builder.show();
    }

    public void setCoin(CoinBean coinBean) {
        tvMyCoin.setText(coinBean.getCoinCount());
        String level = coinBean.getLevel();
        String rank = coinBean.getRank();
        tvMyLevel.setText("等级:"+level+" 排名:"+rank);
    }
}
