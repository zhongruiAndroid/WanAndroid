package com.zr.wanandroid.module.home.fragment;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;

import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.common.listener.CreateFragmentInterface;
import com.zr.wanandroid.test.video.SplashPlayVideoView;

public class HomeFragment extends BaseFragment  {

    private FrameLayout llRoot;

    @Override
    public int getContentView() {
        return R.layout.home_frag;
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {

    }

    @Override
    public void initViewAfter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()){
            case R.id.bt:
            break;
        }
    }
}
