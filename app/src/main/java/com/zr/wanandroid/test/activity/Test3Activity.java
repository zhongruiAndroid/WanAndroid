package com.zr.wanandroid.test.activity;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.test.adapter.FragmentStateAdapter;
import com.zr.wanandroid.test.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class Test3Activity extends BaseActivity {
    FragmentStateAdapter fragmentAdapter;
    ViewPager vp;
    @Override
    public int getContentView() {
        return R.layout.test2_activity;
    }

    @Override
    public void initView() {
        vp=findViewById(R.id.vp);
    }


    @Override
    public void initViewAfter() {
        List<TestFragment> list=new ArrayList<>();
        for (int i =1; i <= 6; i++) {
            TestFragment testFragment = TestFragment.newInstance(i);
            list.add(testFragment);
        }
        fragmentAdapter=new FragmentStateAdapter(getSupportFragmentManager());
        fragmentAdapter.setList(list);


//        vp.setOffscreenPageLimit(list.size()-1);
        vp.setAdapter(fragmentAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onNoDoubleClick(View view) {

    }
}
