package com.zr.wanandroid.common.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentListAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public FragmentListAdapter(FragmentManager fm) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
