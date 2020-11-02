package com.zr.wanandroid.common.net;

public class NetUrl {
    /*首页banner*/
    public static final String HOME_BANNER=NetHost.API_HOST+"banner/json";
    /*首页文章列表*/
    public static final String HOME_ARTICLE_LIST=NetHost.API_HOST+"article/list/%s/json";
    /*首页置顶文章*/
    public static final String HOME_TOP_ARTICLE_LIST=NetHost.API_HOST+"article/top/json";
    /*热门搜索词*/
    public static final String HOME_HOT_SEARCH_RECORD=NetHost.API_HOST+"hotkey/json";
    /*热门搜索词,k ： 搜索关键词*/
    public static final String HOME_SEARCH_ARTICLE=NetHost.API_HOST+"article/query/%s/json";
    /*知识体系数据*/
    public static final String KNOWLEDGE_SYSTEM=NetHost.API_HOST+"tree/json";
    /*知识体系下的文章*/
    public static final String KNOWLEDGE_SYSTEM_ARTICLE=NetHost.API_HOST+"article/list/%s/json";
    /*导航数据*/
    public static final String NAVIGATION_LIST=NetHost.API_HOST+"navi/json";
    /*公众号列表*/
    public static final String OFFICIAL_ACCOUNT_LIST=NetHost.API_HOST+"chapters/json";
}
