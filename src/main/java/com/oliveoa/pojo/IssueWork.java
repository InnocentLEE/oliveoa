package com.oliveoa.pojo;

import java.util.Date;

public class IssueWork {
    private String iwid;

    private String eid;

    private String content;

    private Date begintime;

    private Date endtime;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public IssueWork(String iwid, String eid, String content, Date begintime, Date endtime, Integer orderby, Date createtime, Date updatetime) {
        this.iwid = iwid;
        this.eid = eid;
        this.content = content;
        this.begintime = begintime;
        this.endtime = endtime;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public IssueWork() {
        super();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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