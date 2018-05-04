package com.oliveoa.pojo;

import java.util.Date;

public class LeaveOfficeApplicationApprovedOpinion {
    private String loaaocid;

    private String loaaopid;

    private String loaid;

    private String eid;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public LeaveOfficeApplicationApprovedOpinion(String loaaocid, String loaaopid, String loaid, String eid, Integer isapproved, String opinion, Integer orderby, Date createtime, Date updatetime) {
        this.loaaocid = loaaocid;
        this.loaaopid = loaaopid;
        this.loaid = loaid;
        this.eid = eid;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public LeaveOfficeApplicationApprovedOpinion() {
        super();
    }

    public String getLoaaocid() {
        return loaaocid;
    }

    public void setLoaaocid(String loaaocid) {
        this.loaaocid = loaaocid == null ? null : loaaocid.trim();
    }

    public String getLoaaopid() {
        return loaaopid;
    }

    public void setLoaaopid(String loaaopid) {
        this.loaaopid = loaaopid == null ? null : loaaopid.trim();
    }

    public String getLoaid() {
        return loaid;
    }

    public void setLoaid(String loaid) {
        this.loaid = loaid == null ? null : loaid.trim();
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