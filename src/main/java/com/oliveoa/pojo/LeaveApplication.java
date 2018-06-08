package com.oliveoa.pojo;

import java.util.Date;

public class LeaveApplication {
    private String laid;

    private String eid;

    private Date begintime;

    private Date endtime;

    private String reason;

    private Integer type;

    private Date normalRest;

    private Date swapRest;

    private Date shouldRest;

    private Date supplementRest;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public LeaveApplication(String laid, String eid, Date begintime, Date endtime, String reason, Integer type, Date normalRest, Date swapRest, Date shouldRest, Date supplementRest, Integer orderby, Date createtime, Date updatetime) {
        this.laid = laid;
        this.eid = eid;
        this.begintime = begintime;
        this.endtime = endtime;
        this.reason = reason;
        this.type = type;
        this.normalRest = normalRest;
        this.swapRest = swapRest;
        this.shouldRest = shouldRest;
        this.supplementRest = supplementRest;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public LeaveApplication() {
        super();
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

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getNormalRest() {
        return normalRest;
    }

    public void setNormalRest(Date normalRest) {
        this.normalRest = normalRest;
    }

    public Date getSwapRest() {
        return swapRest;
    }

    public void setSwapRest(Date swapRest) {
        this.swapRest = swapRest;
    }

    public Date getShouldRest() {
        return shouldRest;
    }

    public void setShouldRest(Date shouldRest) {
        this.shouldRest = shouldRest;
    }

    public Date getSupplementRest() {
        return supplementRest;
    }

    public void setSupplementRest(Date supplementRest) {
        this.supplementRest = supplementRest;
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