package com.oliveoa.pojo;

import java.util.Date;

public class FulltimeApplication {
    private String faid;

    private String eid;

    private Date begintime;

    private Date endtime;

    private String personalSummary;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public FulltimeApplication(String faid, String eid, Date begintime, Date endtime, String personalSummary, Integer orderby, Date createtime, Date updatetime) {
        this.faid = faid;
        this.eid = eid;
        this.begintime = begintime;
        this.endtime = endtime;
        this.personalSummary = personalSummary;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public FulltimeApplication() {
        super();
    }

    public String getFaid() {
        return faid;
    }

    public void setFaid(String faid) {
        this.faid = faid == null ? null : faid.trim();
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

    public String getPersonalSummary() {
        return personalSummary;
    }

    public void setPersonalSummary(String personalSummary) {
        this.personalSummary = personalSummary == null ? null : personalSummary.trim();
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