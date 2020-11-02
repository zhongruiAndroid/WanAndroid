package com.zr.wanandroid.module.knowledgesystem.event;

public class SelectKnowledgeEvent {
    public int position;
    public boolean isKnowledgeSystem;
    public SelectKnowledgeEvent(int position, boolean isKnowledgeSystem) {
        this.position = position;
        this.isKnowledgeSystem = isKnowledgeSystem;
    }
}
