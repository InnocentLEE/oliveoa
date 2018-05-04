package com.oliveoa.pojo;

import java.util.Date;

public class OvertimeApplicationApprovedOpinion {
    private String oaaocid;

    private String oaaopid;

    private String oaid;

    private String eid;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public OvertimeApplicationApprovedOpinion(String oaaocid, String oaaopid, String oaid, String eid, Integer isapproved, String opinion, Integer orderby, Date createtime, Date updatetime) {
        this.oaaocid = oaaocid;
        this.oaaopid = oaaopid;
        this.oaid = oaid;
        this.eid = eid;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public OvertimeApplicationApprovedOpinion() {
        super();
    }

    public String getOaaocid() {
        return oaaocid;
    }

    public void setOaaocid(String oaaocid) {
        this.oaaocid = oaaocid == null ? null : oaaocid.trim();
    }

    public String getOaaopid() {
        return oaaopid;
    }

    public void setOaaopid(String oaaopid) {
        this.oaaopid = oaaopid == null ? null : oaaopid.trim();
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid == null ? null : oaid.trim();
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