package com.li.experience.common.core;

import java.util.List;
import java.util.Map;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-06-18 17:24
 **/
public class Tree {
    private String id;
    private String text;
    private String icon;
    private Map state;
    private List<Tree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map getState() {
        return state;
    }

    public void setState(Map state) {
        this.state = state;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
