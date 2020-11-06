package com.zr.wanandroid.module.my.bean;

import java.io.Serializable;

public class CoinRecordBean implements Serializable {

    /**
     * coinCount : 11
     * date : 1604626999000
     * desc : 2020-11-06 09:43:19 签到 , 积分：10 + 1
     * id : 325519
     * reason : 签到
     * type : 1
     * userId : 81275
     * userName : 17621060271
     */

    private String coinCount;
    private String date;
    private String desc;
    private String id;
    private String reason;
    private String type;
    private String userId;
    private String userName;

    public String getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = coinCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
