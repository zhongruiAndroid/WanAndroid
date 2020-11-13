package com.zr.wanandroid.bridge;

import android.app.Activity;
import android.content.Intent;

import com.zr.wanandroid.module.home.activity.SearchActivity;
import com.zr.wanandroid.module.knowledgesystem.activity.KnowledgeSystemActivity;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;
import com.zr.wanandroid.module.my.activity.CoinRankActivity;
import com.zr.wanandroid.module.my.activity.CoinRecordActivity;
import com.zr.wanandroid.module.my.activity.LoginActivity;
import com.zr.wanandroid.module.my.activity.RegisterActivity;
import com.zr.wanandroid.module.officialaccount.activity.OfficialAccountArticleActivity;
import com.zr.wanandroid.module.web.activity.WebActivity;

public class ActBridge {

    public static void toWebActivity(Activity activity, String title, String url) {
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(WebActivity.INTENT_TITLE, title);
        intent.putExtra(WebActivity.INTENT_URL, url);
        activity.startActivity(intent);
    }

    public static void toSearchActivity(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }

    public static void toKnowledgeSystemArticleActivity(Activity activity, KnowledgeSystemBean bean) {
        Intent intent = new Intent(activity, KnowledgeSystemActivity.class);
        intent.putExtra(KnowledgeSystemActivity.INTENT_KNOWLEDGE_SYSTEM, bean);
        activity.startActivity(intent);
    }

    public static void toOfficialAccountArticleActivity(Activity activity, String id, String name) {
        Intent intent = new Intent(activity, OfficialAccountArticleActivity.class);
        intent.putExtra(OfficialAccountArticleActivity.INTENT_AUTHOR_ID, id);
        intent.putExtra(OfficialAccountArticleActivity.INTENT_AUTHOR_NAME, name);
        activity.startActivity(intent);
    }
    public static void toLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
    public static void toRegisterActivity(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }
    public static void toCoinRecordActivity(Activity activity) {
        Intent intent = new Intent(activity, CoinRecordActivity.class);
        activity.startActivity(intent);
    }
    public static void toCoinRankActivity(Activity activity) {
        Intent intent = new Intent(activity, CoinRankActivity.class);
        activity.startActivity(intent);
    }
}
