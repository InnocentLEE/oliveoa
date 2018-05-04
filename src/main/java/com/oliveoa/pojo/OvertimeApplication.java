package com.oliveoa.pojo;

import java.util.Date;

public class OvertimeApplication {
    private String oaid;

    private String eid;

    private String reason;

    private Date begintime;

    private Date endtime;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public OvertimeApplication(String oaid, String eid, String reason, Date begintime, Date endtime, Integer orderby, Date createtime, Date updatetime) {
        this.oaid = oaid;
        this.eid = eid;
        this.reason = reason;
        this.begintime = begintime;
        this.endtime = endtime;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public OvertimeApplication() {
        super();
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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