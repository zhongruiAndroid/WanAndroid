package com.zr.wanandroid.module.knowledgesystem.bean;

import java.io.Serializable;
import java.util.List;

public class KnowledgeSystemBean implements Serializable {
    /**
     * children : [{"children":[],"courseId":13,"id":533,"name":" HarmonyOS","order":250000,"parentChapterId":532,"userControlSetTop":false,"visible":1}]
     * courseId : 13
     * id : 532
     * name : 鸿蒙
     * order : 250
     * parentChapterId : 0
     * userControlSetTop : false
     * visible : 1
     */

    private String courseId;
    private String id;
    private String name;
    private String order;
    private String parentChapterId;
    private String userControlSetTop;
    private String visible;
    private List<ChildrenBean> children;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(String parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public String getUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(String userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean implements Serializable {
        /**
         * children : []
         * courseId : 13
         * id : 533
         * name :  HarmonyOS
         * order : 250000
         * parentChapterId : 532
         * userControlSetTop : false
         * visible : 1
         */

        private String courseId;
        private String id;
        private String name;
        private String order;
        private String parentChapterId;
        private String userControlSetTop;
        private String visible;
        private List<?> children;

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(String parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public String getUserControlSetTop() {
            return userControlSetTop;
        }

        public void setUserControlSetTop(String userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public String getVisible() {
            return visible;
        }

        public void setVisible(String visible) {
            this.visible = visible;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}
