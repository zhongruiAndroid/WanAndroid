package com.zr.wanandroid.module.project.presenter;

import com.github.developtools.N;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;
import com.zr.wanandroid.module.project.fragment.ProjectClassifyFragment;
import com.zr.wanandroid.module.project.model.ProjectClassifyModel;

import java.util.List;

public class ProjectClassifyPresenter extends BasePresenter<ProjectClassifyFragment> {

    public void getData(int page, boolean isLoad) {
        ProjectClassifyModel.getInstance().getProjectClassify(new RequestListener<List<KnowledgeSystemBean>>() {
            @Override
            public void onSuccess(List<KnowledgeSystemBean> data) {
                if(N.isEmpty(data)){
                    getView().showEmpty();
                    return;
                }
                getView().setProjectClassifyData(data);
            }
            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
                getView().showError();
            }
        });
    }
}
