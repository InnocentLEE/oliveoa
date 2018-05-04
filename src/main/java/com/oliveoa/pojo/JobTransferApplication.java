package com.oliveoa.pojo;

import java.util.Date;

public class JobTransferApplication {
    private String jtaid;

    private String aeid;

    private String eid;

    private String aimdcid;

    private String aimpcid;

    private String reason;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public JobTransferApplication(String jtaid, String aeid, String eid, String aimdcid, String aimpcid, String reason, Integer orderby, Date createtime, Date updatetime) {
        this.jtaid = jtaid;
        this.aeid = aeid;
        this.eid = eid;
        this.aimdcid = aimdcid;
        this.aimpcid = aimpcid;
        this.reason = reason;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public JobTransferApplication() {
        super();
    }

    public String getJtaid() {
        return jtaid;
    }

    public void setJtaid(String jtaid) {
        this.jtaid = jtaid == null ? null : jtaid.trim();
    }

    public String getAeid() {
        return aeid;
    }

    public void setAeid(String aeid) {
        this.aeid = aeid == null ? null : aeid.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public String getAimdcid() {
        return aimdcid;
    }

    public void setAimdcid(String aimdcid) {
        this.aimdcid = aimdcid == null ? null : aimdcid.trim();
    }

    public String getAimpcid() {
        return aimpcid;
    }

    public void setAimpcid(String aimpcid) {
        this.aimpcid = aimpcid == null ? null : aimpcid.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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