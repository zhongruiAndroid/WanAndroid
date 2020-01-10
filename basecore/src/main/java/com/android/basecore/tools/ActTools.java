package com.android.basecore.tools;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ActTools {

    public static void addFragment(FragmentActivity activity, @IdRes int resId, Fragment fragment) {
        if (fragment != null && fragment.isAdded() == false) {
            activity.getSupportFragmentManager().beginTransaction().add(resId, fragment).commitAllowingStateLoss();
        }
    }

    public static void replaceFragment(FragmentActivity activity, @IdRes int resId, Fragment fragment) {
        if (fragment != null) {
            activity.getSupportFragmentManager().beginTransaction().replace(resId, fragment).commitAllowingStateLoss();
        }
    }

    public static void hideFragment(FragmentActivity activity, Fragment fragment) {
        if (fragment != null && !fragment.isHidden()) {
            activity.getSupportFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
        }
    }

    public static void showFragment(FragmentActivity activity, Fragment fragment) {
        if (fragment != null) {
            activity.getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        }
    }
}
