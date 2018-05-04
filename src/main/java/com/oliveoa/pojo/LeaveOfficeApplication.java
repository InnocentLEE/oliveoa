package com.oliveoa.pojo;

import java.util.Date;

public class LeaveOfficeApplication {
    private String loaid;

    private String eid;

    private Date leavetime;

    private String reason;

    private String handoverMatters;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public LeaveOfficeApplication(String loaid, String eid, Date leavetime, String reason, String handoverMatters, Integer orderby, Date createtime, Date updatetime) {
        this.loaid = loaid;
        this.eid = eid;
        this.leavetime = leavetime;
        this.reason = reason;
        this.handoverMatters = handoverMatters;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public LeaveOfficeApplication() {
        super();
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

    public Date getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getHandoverMatters() {
        return handoverMatters;
    }

    public void setHandoverMatters(String handoverMatters) {
        this.handoverMatters = handoverMatters == null ? null : handoverMatters.trim();
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