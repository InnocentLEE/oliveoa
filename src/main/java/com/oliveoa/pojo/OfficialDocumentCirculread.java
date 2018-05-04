package com.oliveoa.pojo;

import java.util.Date;

public class OfficialDocumentCirculread {
    private String odcid;

    private String oiid;

    private String eid;

    private Integer isread;

    private String report;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public OfficialDocumentCirculread(String odcid, String oiid, String eid, Integer isread, String report, Integer orderby, Date createtime, Date updatetime) {
        this.odcid = odcid;
        this.oiid = oiid;
        this.eid = eid;
        this.isread = isread;
        this.report = report;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public OfficialDocumentCirculread() {
        super();
    }

    public String getOdcid() {
        return odcid;
    }

    public void setOdcid(String odcid) {
        this.odcid = odcid == null ? null : odcid.trim();
    }

    public String getOiid() {
        return oiid;
    }

    public void setOiid(String oiid) {
        this.oiid = oiid == null ? null : oiid.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report == null ? null : report.trim();
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