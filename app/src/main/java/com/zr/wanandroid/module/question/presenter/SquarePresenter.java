package com.zr.wanandroid.module.question.presenter;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.github.developtools.N;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.question.fragment.SquareFragment;
import com.zr.wanandroid.module.question.model.QuestionModel;

import java.util.List;

public class SquarePresenter extends BasePresenter<SquareFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private HomeAdapter adapter;

    public HomeAdapter initAdapter() {
        adapter = new HomeAdapter(getView().getActivity());
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }

    public void getData(int page, boolean isLoad) {
        QuestionModel.getInstance().getSquareList(page, new BaseRequestListener<List<HomeArticleBean>, Boolean>() {
            @Override
            public void onSuccess(List<HomeArticleBean> data) {
                loadResult(isLoad);
                if (N.isEmpty(data)) {
                    getView().showEmpty();
                    return;
                }
                if (!isLoad) {
                    adapter.setList(data, true);
                    getView().showContent();
                } else {
                    adapter.addList(data, true);
                }
            }

            @Override
            public void onCustomSuccess(List<HomeArticleBean> data, Boolean custom) {
                adapter.loadHasMore(custom);
            }

            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
                if (!isLoad) {
                    getView().showError();
                }
            }
        });
    }

    @Override
    public void loadMore(LoadListener loadListener) {
        getData(pageNum, true);
    }
}
