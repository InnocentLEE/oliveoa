package com.oliveoa.pojo;

import java.util.Date;

public class AnnouncementApprovedOpinion {
    private String aaocid;

    private String aaopid;

    private String aid;

    private String eid;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public AnnouncementApprovedOpinion(String aaocid, String aaopid, String aid, String eid, Integer isapproved, String opinion, Integer orderby, Date createtime, Date updatetime) {
        this.aaocid = aaocid;
        this.aaopid = aaopid;
        this.aid = aid;
        this.eid = eid;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public AnnouncementApprovedOpinion() {
        super();
    }

    public String getAaocid() {
        return aaocid;
    }

    public void setAaocid(String aaocid) {
        this.aaocid = aaocid == null ? null : aaocid.trim();
    }

    public String getAaopid() {
        return aaopid;
    }

    public void setAaopid(String aaopid) {
        this.aaopid = aaopid == null ? null : aaopid.trim();
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
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