package com.oliveoa.pojo;

import java.util.Date;

public class RecruitmentApplicationApprovedOpinion {
    private String raaocid;

    private String raaopid;

    private String raid;

    private String eid;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public RecruitmentApplicationApprovedOpinion(String raaocid, String raaopid, String raid, String eid, Integer isapproved, String opinion, Integer orderby, Date createtime, Date updatetime) {
        this.raaocid = raaocid;
        this.raaopid = raaopid;
        this.raid = raid;
        this.eid = eid;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public RecruitmentApplicationApprovedOpinion() {
        super();
    }

    public String getRaaocid() {
        return raaocid;
    }

    public void setRaaocid(String raaocid) {
        this.raaocid = raaocid == null ? null : raaocid.trim();
    }

    public String getRaaopid() {
        return raaopid;
    }

    public void setRaaopid(String raaopid) {
        this.raaopid = raaopid == null ? null : raaopid.trim();
    }

    public String getRaid() {
        return raid;
    }

    public void setRaid(String raid) {
        this.raid = raid == null ? null : raid.trim();
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