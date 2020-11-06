package com.zr.wanandroid.module.my.bean;

import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable {
    /**
     * admin : false
     * chapterTops : []
     * coinCount : 0
     * collectIds : []
     * email :
     * icon :
     * id : 81275
     * nickname : 17621060271
     * password :
     * publicName : 17621060271
     * token :
     * type : 0
     * username : 17621060271
     */

    private String admin;
    private String coinCount;
    private String email;
    private String icon;
    private String id;
    private String nickname;
    private String password;
    private String publicName;
    private String token;
    private String type;
    private String username;
    private List<?> chapterTops;
    private List<?> collectIds;
    private CoinBean coinBean;

    public CoinBean getCoinBean() {
        return coinBean==null?new CoinBean():coinBean;
    }

    public void setCoinBean(CoinBean coinBean) {
        this.coinBean = coinBean;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = coinCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<?> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<?> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List<?> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<?> collectIds) {
        this.collectIds = collectIds;
    }
}
