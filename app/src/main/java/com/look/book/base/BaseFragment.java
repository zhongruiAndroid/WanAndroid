package com.look.book.base;

import android.view.View;

import com.android.basecore.act.MVPBaseActivity;
import com.android.basecore.fragment.MVPBaseFragment;
import com.android.basecore.fragment._BaseFragment;
import com.android.basecore.presenter._BasePresenter;

public abstract class BaseFragment<P extends _BasePresenter> extends MVPBaseFragment<P> {

    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }
}
