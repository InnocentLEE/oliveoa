package com.oliveoa.pojo;

import java.util.Date;

public class IssueWorkMember {
    private String iwmid;

    private String iwid;

    private String eid;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public IssueWorkMember(String iwmid, String iwid, String eid, Integer orderby, Date createtime, Date updatetime) {
        this.iwmid = iwmid;
        this.iwid = iwid;
        this.eid = eid;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public IssueWorkMember() {
        super();
    }

    public String getIwmid() {
        return iwmid;
    }

    public void setIwmid(String iwmid) {
        this.iwmid = iwmid == null ? null : iwmid.trim();
    }

    public String getIwid() {
        return iwid;
    }

    public void setIwid(String iwid) {
        this.iwid = iwid == null ? null : iwid.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}