package com.zr.wanandroid.module.officialaccount.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.basecore.listener.NoDoubleClickListener;
import com.github.developtools.DensityUtils;
import com.github.developtools.N;
import com.github.dividerline.BaseItemDivider;
import com.github.fastshape.MyEditText;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.helper.ViewHelper;
import com.zr.wanandroid.module.officialaccount.presenter.OfficialAccountSearchPresenter;

public class OfficialAccountArticleActivity extends BaseActivity<OfficialAccountSearchPresenter> {
    public static final String INTENT_AUTHOR_NAME = "INTENT_AUTHOR_NAME";
    public static final String INTENT_AUTHOR_ID = "INTENT_AUTHOR_ID";
    private MyEditText etOfficialAccountArticleSearch;
    private ImageView ivSearchClear;
    private String authorName;
    private String authorId;
    private RecyclerView rvOfficialAccountArticleSearchByAuthor;

    @Override
    public int getContentView() {
        return R.layout.official_account_article_act;
    }

    @Override
    public void initView() {
        titleView.setAppRightTitle("搜索");
        titleView.setAppRightTitleColor(color(R.color.c_white));
        titleView.setAppRightTitleOnClickListener(this);


        authorId = getIntent().getStringExtra(INTENT_AUTHOR_ID);
        authorName = getIntent().getStringExtra(INTENT_AUTHOR_NAME);

        etOfficialAccountArticleSearch = findViewById(R.id.etOfficialAccountArticleSearch);
        etOfficialAccountArticleSearch.setHint("搜索" + authorName + "公众号文章");


        rvOfficialAccountArticleSearchByAuthor = findViewById(R.id.rvOfficialAccountArticleSearchByAuthor);
        ivSearchClear = findViewById(R.id.ivSearchClear);
        ivSearchClear.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View var1) {
                etOfficialAccountArticleSearch.setText("");
            }
        });
        ViewHelper.setVisibility(ivSearchClear, View.INVISIBLE);
    }

    @Override
    public void initViewAfter() {
        etOfficialAccountArticleSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchArticleByAuthor();
                    return true;
                }
                return false;
            }

        });
        etOfficialAccountArticleSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (N.trimToEmptyNull(s.toString())) {
                    ViewHelper.setVisibility(ivSearchClear, View.INVISIBLE);
                } else {
                    ViewHelper.setVisibility(ivSearchClear, View.VISIBLE);
                }
            }
        });

        rvOfficialAccountArticleSearchByAuthor = findViewById(R.id.rvOfficialAccountArticleSearchByAuthor);
        rvOfficialAccountArticleSearchByAuthor.setLayoutManager(new LinearLayoutManager(mActivity));
        rvOfficialAccountArticleSearchByAuthor.addItemDecoration(new BaseItemDivider(mActivity, DensityUtils.dp2px(20)));
        HomeAdapter adapter = getPresenter().initAdapter();
        rvOfficialAccountArticleSearchByAuthor.setAdapter(adapter);

    }

    private void searchArticleByAuthor() {
        String text = etOfficialAccountArticleSearch.getText().toString();
        getPresenter().getData(1, false, authorId, text);
    }

    @Override
    public void initData() {
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(page, isLoad, authorId, null);
    }

    @Override
    public void onNoDoubleClick(View v) {
        switch (v.getId()) {
            case R.id.appTvRightTitle:
                searchArticleByAuthor();
                break;
        }
    }
}
