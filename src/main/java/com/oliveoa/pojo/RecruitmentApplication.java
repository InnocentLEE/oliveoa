package com.oliveoa.pojo;

import java.util.Date;

public class RecruitmentApplication {
    private String raid;

    private String dcid;

    private String eid;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public RecruitmentApplication(String raid, String dcid, String eid, Integer orderby, Date createtime, Date updatetime) {
        this.raid = raid;
        this.dcid = dcid;
        this.eid = eid;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public RecruitmentApplication() {
        super();
    }

    public String getRaid() {
        return raid;
    }

    public void setRaid(String raid) {
        this.raid = raid == null ? null : raid.trim();
    }

    public String getDcid() {
        return dcid;
    }

    public void setDcid(String dcid) {
        this.dcid = dcid == null ? null : dcid.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
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