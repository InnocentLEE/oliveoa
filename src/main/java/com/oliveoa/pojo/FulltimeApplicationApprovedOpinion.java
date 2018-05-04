package com.oliveoa.pojo;

import java.util.Date;

public class FulltimeApplicationApprovedOpinion {
    private String faaocid;

    private String faaopid;

    private String faid;

    private String eid;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public FulltimeApplicationApprovedOpinion(String faaocid, String faaopid, String faid, String eid, Integer isapproved, String opinion, Integer orderby, Date createtime, Date updatetime) {
        this.faaocid = faaocid;
        this.faaopid = faaopid;
        this.faid = faid;
        this.eid = eid;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public FulltimeApplicationApprovedOpinion() {
        super();
    }

    public String getFaaocid() {
        return faaocid;
    }

    public void setFaaocid(String faaocid) {
        this.faaocid = faaocid == null ? null : faaocid.trim();
    }

    public String getFaaopid() {
        return faaopid;
    }

    public void setFaaopid(String faaopid) {
        this.faaopid = faaopid == null ? null : faaopid.trim();
    }

    public String getFaid() {
        return faid;
    }

    public void setFaid(String faid) {
        this.faid = faid == null ? null : faid.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public Integer getIsapproved() {
        return isapproved;
    }

    public void setIsapproved(Integer isapproved) {
        this.isapproved = isapproved;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
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