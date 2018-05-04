package com.oliveoa.pojo;

import java.util.Date;

public class OfficialDocumentIssued {
    private String odiid;

    private String oiid;

    private String dcid;

    private Integer isreceive;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public OfficialDocumentIssued(String odiid, String oiid, String dcid, Integer isreceive, Integer orderby, Date createtime, Date updatetime) {
        this.odiid = odiid;
        this.oiid = oiid;
        this.dcid = dcid;
        this.isreceive = isreceive;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public OfficialDocumentIssued() {
        super();
    }

    public String getOdiid() {
        return odiid;
    }

    public void setOdiid(String odiid) {
        this.odiid = odiid == null ? null : odiid.trim();
    }

    public String getOiid() {
        return oiid;
    }

    public void setOiid(String oiid) {
        this.oiid = oiid == null ? null : oiid.trim();
    }

    public String getDcid() {
        return dcid;
    }

    public void setDcid(String dcid) {
        this.dcid = dcid == null ? null : dcid.trim();
    }

    public Integer getIsreceive() {
        return isreceive;
    }

    public void setIsreceive(Integer isreceive) {
        this.isreceive = isreceive;
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