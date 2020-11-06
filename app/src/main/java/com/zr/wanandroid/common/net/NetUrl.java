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
    public static final String OFFICIAL_ACCOUNT_LIST=NetHost.API_HOST+"wxarticle/chapters/json";
    /*公众号作者文章列表*/
    public static final String OFFICIAL_ACCOUNT_ARTICLE_LIST=NetHost.API_HOST+"wxarticle/list/%s/%s/json";
    /*问答*/
    public static final String QUESTION_ANSWER=NetHost.API_HOST+"wenda/list/%s/json";
    /*广场*/
    public static final String SQUARE_LIST=NetHost.API_HOST+"user_article/list/%s/json";
    /*项目分类*/
    public static final String PROJECT_CLASSIFY=NetHost.API_HOST+"project/tree/json";
    /*项目列表*/
    public static final String PROJECT_LIST=NetHost.API_HOST+"project/list/%s/json";
    /*登录*/
    public static final String USER_LOGIN=NetHost.API_HOST+"user/login";
    /*注册*/
    public static final String USER_REGISTER=NetHost.API_HOST+"user/register";
    /*退出登录*/
    public static final String LOGOUT=NetHost.API_HOST+"user/logout/json";
    /*个人积分*/
    public static final String USER_COIN=NetHost.API_HOST+"lg/coin/userinfo/json";
    /*个人积分获取列表*/
    public static final String USER_COIN_RECORD=NetHost.API_HOST+"lg/coin/list/%s/json";
    /*积分排行榜*/
    public static final String COIN_LIST=NetHost.API_HOST+"coin/rank/%s/json";
}
