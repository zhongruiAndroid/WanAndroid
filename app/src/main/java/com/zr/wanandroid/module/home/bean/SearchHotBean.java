package com.zr.wanandroid.module.home.bean;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.github.developtools.N;
import com.github.developtools.StringUtils;

import java.io.Serializable;

public class SearchHotBean implements Serializable {
    /**
     * id : 6
     * link :
     * name : 面试
     * order : 1
     * visible : 1
     */

    private String id;
    private String link;
    private String name;
    private String order;
    private String visible;

    public SearchHotBean() {
    }

    public SearchHotBean(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj==this){
            return true;
        }
        if(! (obj instanceof SearchHotBean)){
            return false;
        }
        SearchHotBean hotBean= (SearchHotBean )obj;
        if(TextUtils.equals(hotBean.name,this.name)){
            return true;
        }
        return super.equals(obj);
    }
}
