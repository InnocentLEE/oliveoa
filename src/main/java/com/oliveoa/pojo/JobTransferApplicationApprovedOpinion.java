package com.oliveoa.pojo;

import java.util.Date;

public class JobTransferApplicationApprovedOpinion {
    private String jtaaocid;

    private String jtaaopid;

    private String jtaid;

    private String eid;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public JobTransferApplicationApprovedOpinion(String jtaaocid, String jtaaopid, String jtaid, String eid, Integer isapproved, String opinion, Integer orderby, Date createtime, Date updatetime) {
        this.jtaaocid = jtaaocid;
        this.jtaaopid = jtaaopid;
        this.jtaid = jtaid;
        this.eid = eid;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public JobTransferApplicationApprovedOpinion() {
        super();
    }

    public String getJtaaocid() {
        return jtaaocid;
    }

    public void setJtaaocid(String jtaaocid) {
        this.jtaaocid = jtaaocid == null ? null : jtaaocid.trim();
    }

    public String getJtaaopid() {
        return jtaaopid;
    }

    public void setJtaaopid(String jtaaopid) {
        this.jtaaopid = jtaaopid == null ? null : jtaaopid.trim();
    }

    public String getJtaid() {
        return jtaid;
    }

    public void setJtaid(String jtaid) {
        this.jtaid = jtaid == null ? null : jtaid.trim();
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