package com.look.book.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list=new ArrayList<>();

    public void setList(List  list) {
        this.list = list;
    }
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
}
