package com.zr.wanandroid.module.project.presenter;

import android.view.View;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.github.adapter.listener.NoDoubleAdapterOnClickListener;
import com.github.developtools.N;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;
import com.zr.wanandroid.module.project.adapter.ProjectListAdapter;
import com.zr.wanandroid.module.project.fragment.ProjectListFragment;
import com.zr.wanandroid.module.project.model.ProjectClassifyModel;

import java.util.List;

public class ProjectListPresenter extends BasePresenter<ProjectListFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private ProjectListAdapter adapter;
    private String id;
    public LoadMoreAdapter initAdapter(){
        adapter=new ProjectListAdapter(1);
        adapter.setOnItemClickListener(new NoDoubleAdapterOnClickListener() {
            @Override
            public void onNoDoubleClick(View view, int i) {

            }
        });
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }
    public void getData(int page, boolean isLoad,String id) {
        this.id=id;
        ProjectClassifyModel.getInstance().getProjectList(page, id, new BaseRequestListener<List<HomeArticleBean>, Boolean>() {
            @Override
            public void onSuccess(List<HomeArticleBean> data) {
                loadResult(isLoad);
                if(!isLoad){
                    if(N.isEmpty(data)){
                        getView().showEmpty();
                        return;
                    }
                    adapter.setList(data,true);
                    getView().showContent();
                    getView().getProjectSuccess();
                }else{
                    adapter.addList(data,true);
                }
            }
            @Override
            public void onCustomSuccess(List<HomeArticleBean> data, Boolean custom) {
                adapter.loadHasMore(custom);
            }
            @Override
            public void onError(String code, String errorMsg) {
                loadError(adapter);
                showToast(errorMsg);
                if(!isLoad){
                    getView().showError();
                }
            }
        });
    }

    @Override
    public void loadMore(LoadListener loadListener) {
        getData(pageNum,true,this.id);
    }
}
