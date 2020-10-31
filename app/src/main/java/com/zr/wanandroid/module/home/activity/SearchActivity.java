package com.zr.wanandroid.module.home.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.android.basecore.tools.ActTools;
import com.github.developtools.N;
import com.github.fastshape.MyEditText;
import com.github.interbus.BusCallback;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.module.home.event.SearchArticleEvent;
import com.zr.wanandroid.module.home.fragment.SearchHistoryFragment;
import com.zr.wanandroid.module.home.fragment.SearchResultFragment;
import com.zr.wanandroid.module.home.helper.ViewHelper;
import com.zr.wanandroid.module.home.presenter.SearchPresenter;

public class SearchActivity extends BaseActivity<SearchPresenter> {
    private MyEditText etHomeSearch;
    private View ivSearchClear;
    private SearchHistoryFragment searchHistoryFragment;
    private SearchResultFragment searchResultFragment;

    @Override
    public int getContentView() {
        return R.layout.search_act;
    }

    @Override
    public void initView() {
        titleView.setAppRightTitle("搜索");
        titleView.setAppRightTitleColor(color(R.color.c_white));
        titleView.setAppRightTitleOnClickListener(this);

        etHomeSearch = findViewById(R.id.etHomeSearch);
        ivSearchClear = findViewById(R.id.ivSearchClear);
        ivSearchClear.setOnClickListener(this);
        ViewHelper.setVisibility(ivSearchClear,View.INVISIBLE);
        etHomeSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchByText();
                    return true;
                }
                return false;
            }

        });
        etHomeSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (N.trimToEmptyNull(s.toString())) {
                    showHistorySearch();
                    ViewHelper.setVisibility(ivSearchClear,View.INVISIBLE);
                }else{
                    ViewHelper.setVisibility(ivSearchClear,View.VISIBLE);
                }
            }
        });
        searchHistoryFragment = SearchHistoryFragment.newInstance();
        ActTools.addFragment(this, R.id.flSearchContent, searchHistoryFragment);
    }

    private void showHistorySearch() {
        if (searchHistoryFragment != null) {
            searchHistoryFragment.getHistorySearch();
        }
        ActTools.showFragment(this, searchHistoryFragment);
        ActTools.hideFragment(this, searchResultFragment);
    }

    private void showSearchResult(String searchText) {
        ActTools.hideFragment(this, searchHistoryFragment);
        if(searchResultFragment==null){
            searchResultFragment=SearchResultFragment.newInstance(searchText);
            ActTools.addFragment(this, R.id.flSearchContent, searchResultFragment);
        }else{
            searchResultFragment.searchByText(searchText);
            ActTools.showFragment(this, searchResultFragment);
        }

        getPresenter().addSearchToCache(searchText);
        if (searchHistoryFragment != null) {
            searchHistoryFragment.getHistorySearch();
        }
    }

    @Override
    public void initBus() {
        setEvent(SearchArticleEvent.class, new BusCallback<SearchArticleEvent>() {
            @Override
            public void accept(SearchArticleEvent event) {
                etHomeSearch.setText(event.text);
                showSearchResult(event.text);
            }
        });
    }

    @Override
    public void initData() {

    }

    private void searchByText() {
        String text = etHomeSearch.getText().toString();
        if (N.isEmpty(text)) {
            showToast("请输入搜索内容");
            return;
        }
        showSearchResult(text);
    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()) {
            case R.id.appTvRightTitle:
                searchByText();
                break;
            case R.id.ivSearchClear:
                etHomeSearch.setText("");
                break;
        }
    }
}
