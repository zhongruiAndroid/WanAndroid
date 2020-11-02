package com.zr.wanandroid.bridge;

import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;
import com.zr.wanandroid.module.home.activity.SearchActivity;
import com.zr.wanandroid.module.knowledgesystem.activity.KnowledgeSystemActivity;
import com.zr.wanandroid.module.knowledgesystem.bean.KnowledgeSystemBean;
import com.zr.wanandroid.module.officialaccount.activity.OfficialAccountArticleActivity;
import com.zr.wanandroid.module.officialaccount.bean.OfficialAccountBean;
import com.zr.wanandroid.module.web.activity.WebActivity;
import com.zr.wanandroid.test.activity.EmptyActivity;
import com.zr.wanandroid.test.activity.Test2Activity;
import com.zr.wanandroid.test.activity.Test3Activity;
import com.zr.wanandroid.test.activity.TestActivity;

import java.util.List;

public class ActBridge {
    public static void toTestActivity(Activity activity) {
        activity.startActivity(new Intent(activity, TestActivity.class));
    }

    public static void toTest2Activity(Activity activity) {
        activity.startActivity(new Intent(activity, Test2Activity.class));
    }

    public static void toTest3Activity(Activity activity) {
        activity.startActivity(new Intent(activity, Test3Activity.class));
    }

    public static void toEmptyActivity(Activity activity) {
        activity.startActivity(new Intent(activity, EmptyActivity.class));
    }

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
    public static void toOfficialAccountArticleActivity(Activity activity, List<OfficialAccountBean> bean) {
        Intent intent = new Intent(activity, OfficialAccountArticleActivity.class);
        intent.putExtra(OfficialAccountArticleActivity.INTENT_AUTHOR_ID, new Gson().toJson(bean));
        activity.startActivity(intent);
    }
}
