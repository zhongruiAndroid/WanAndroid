package com.zr.wanandroid.common.manager;

import com.github.developtools.N;
import com.github.developtools.SPUtils;
import com.google.gson.Gson;
import com.zr.wanandroid.AppXml;
import com.zr.wanandroid.module.my.bean.UserBean;

public class UserManager {
    /**********************************************************/
    private static UserManager singleObj;
    private UserManager() {
    }
    public static UserManager get(){
        if(singleObj==null){
            synchronized (UserManager.class){
                if(singleObj==null){
                    singleObj=new UserManager();
                }
            }
        }
        return singleObj;
    }
    /**********************************************************/
    private UserBean userBean;
    public UserBean getUser(){
        if(userBean==null){
            String userJson= SPUtils.getString(AppXml.USER_KEY,"");
            userBean=new Gson().fromJson(userJson,UserBean.class);
        }
        return userBean;
    }
    public void setUser(String userJson){
        if(N.trimToEmptyNull(userJson)){
            return;
        }
        userBean=null;
        SPUtils.setPrefString(AppXml.USER_KEY,userJson);
    }

    public static boolean noLogin(){
        return UserManager.get().getUser()==null;
    }
}
