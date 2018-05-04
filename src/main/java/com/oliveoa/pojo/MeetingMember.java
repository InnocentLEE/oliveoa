package com.oliveoa.pojo;

import java.util.Date;

public class MeetingMember {
    private String maid;

    private String eid;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public MeetingMember(String maid, String eid, Integer orderby, Date createtime, Date updatetime) {
        this.maid = maid;
        this.eid = eid;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public MeetingMember() {
        super();
    }

    public String getMaid() {
        return maid;
    }

    public void setMaid(String maid) {
        this.maid = maid == null ? null : maid.trim();
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