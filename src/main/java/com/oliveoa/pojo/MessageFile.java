package com.oliveoa.pojo;

import java.util.Date;

public class MessageFile {
    private String mfid;

    private String mid;

    private String fid;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public MessageFile(String mfid, String mid, String fid, Integer orderby, Date createtime, Date updatetime) {
        this.mfid = mfid;
        this.mid = mid;
        this.fid = fid;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public MessageFile() {
        super();
    }

    public String getMfid() {
        return mfid;
    }

    public void setMfid(String mfid) {
        this.mfid = mfid == null ? null : mfid.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
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