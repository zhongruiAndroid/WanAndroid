package com.look.book.base;

import android.view.View;

import com.android.basecore.fragment._BaseFragment;

public abstract class BaseFragment extends _BaseFragment {

    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }
}
