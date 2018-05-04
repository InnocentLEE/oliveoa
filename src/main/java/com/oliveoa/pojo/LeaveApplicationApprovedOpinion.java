package com.oliveoa.pojo;

import java.util.Date;

public class LeaveApplicationApprovedOpinion {
    private String laaocid;

    private String laaopid;

    private String laid;

    private String eid;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public LeaveApplicationApprovedOpinion(String laaocid, String laaopid, String laid, String eid, Integer isapproved, String opinion, Integer orderby, Date createtime, Date updatetime) {
        this.laaocid = laaocid;
        this.laaopid = laaopid;
        this.laid = laid;
        this.eid = eid;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public LeaveApplicationApprovedOpinion() {
        super();
    }

    public String getLaaocid() {
        return laaocid;
    }

    public void setLaaocid(String laaocid) {
        this.laaocid = laaocid == null ? null : laaocid.trim();
    }

    public String getLaaopid() {
        return laaopid;
    }

    public void setLaaopid(String laaopid) {
        this.laaopid = laaopid == null ? null : laaopid.trim();
    }

    public String getLaid() {
        return laid;
    }

    public void setLaid(String laid) {
        this.laid = laid == null ? null : laid.trim();
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