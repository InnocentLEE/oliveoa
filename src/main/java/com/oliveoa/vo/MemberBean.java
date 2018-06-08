package com.oliveoa.vo;

/**
 * Created by Lee on 2018/6/6.
 */
public class MemberBean {
    private String id;

    public MemberBean(String id) {
        this.id = id;
    }

    public MemberBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MemberBean{" +
                "id='" + id + '\'' +
                '}';
    }
}
