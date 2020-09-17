package com.look.book.base;

import android.support.annotation.IdRes;
import android.view.View;

import com.android.basecore.fragment.MVPBaseFragment;
import com.android.basecore.presenter._BasePresenter;

public abstract class BaseFragment<P extends _BasePresenter> extends MVPBaseFragment<P> {

}
