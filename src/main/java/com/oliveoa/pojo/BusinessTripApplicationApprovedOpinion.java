package com.oliveoa.pojo;

import java.util.Date;

public class BusinessTripApplicationApprovedOpinion {
    private String btaaocid;

    private String btaaopid;

    private String btaid;

    private String eid;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public BusinessTripApplicationApprovedOpinion(String btaaocid, String btaaopid, String btaid, String eid, Integer isapproved, String opinion, Integer orderby, Date createtime, Date updatetime) {
        this.btaaocid = btaaocid;
        this.btaaopid = btaaopid;
        this.btaid = btaid;
        this.eid = eid;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public BusinessTripApplicationApprovedOpinion() {
        super();
    }

    public String getBtaaocid() {
        return btaaocid;
    }

    public void setBtaaocid(String btaaocid) {
        this.btaaocid = btaaocid == null ? null : btaaocid.trim();
    }

    public String getBtaaopid() {
        return btaaopid;
    }

    public void setBtaaopid(String btaaopid) {
        this.btaaopid = btaaopid == null ? null : btaaopid.trim();
    }

    public String getBtaid() {
        return btaid;
    }

    public void setBtaid(String btaid) {
        this.btaid = btaid == null ? null : btaid.trim();
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