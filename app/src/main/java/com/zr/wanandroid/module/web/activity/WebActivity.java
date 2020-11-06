package com.zr.wanandroid.module.web.activity;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.developtools.N;
import com.github.developtools.WebViewUtils;
import com.github.progress.MyProgress;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.common.net.cookie.WebViewCookieUtils;
import com.zr.wanandroid.module.home.helper.ViewHelper;

public class WebActivity extends BaseActivity {
    public static final String INTENT_URL = "INTENT_URL";
    public static final String INTENT_TITLE = "INTENT_TITLE";


    private WebView webView;
    private MyProgress webProgress;

    private String url;
    private String title;

    @Override
    public int getContentView() {
        return R.layout.web_act;
    }

    @Override
    public void initView() {
        url = getIntent().getStringExtra(INTENT_URL);
        title = getIntent().getStringExtra(INTENT_TITLE);

        webView = findViewById(R.id.webView);
        webProgress = findViewById(R.id.webProgress);
        webProgress.setOnProgressInter(new MyProgress.OnProgressInter() {
            @Override
            public void progress(float scaleProgress, float progress, float max) {
                if (scaleProgress >= 100) {
                    ViewHelper.setVisibility(webProgress, View.INVISIBLE, 500);
                }
            }
        });


        titleView.setAppTitle(title);
    }

    @Override
    public void initData() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (webProgress != null) {
                    webProgress.setNowProgress(newProgress);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                WebViewCookieUtils.get().setCookie(url);
                return super.shouldInterceptRequest(view, url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!N.trimToEmptyNull(url) && !url.startsWith("http")) {
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl(url);
        WebViewUtils.configWebViewSetting(webView, false);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebViewUtils.onDestroy(webView);
    }
}
