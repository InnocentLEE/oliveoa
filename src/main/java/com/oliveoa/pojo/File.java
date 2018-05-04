package com.oliveoa.pojo;

import java.util.Date;

public class File {
    private String fid;

    private String fname;

    private String fsrc;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public File(String fid, String fname, String fsrc, Integer orderby, Date createtime, Date updatetime) {
        this.fid = fid;
        this.fname = fname;
        this.fsrc = fsrc;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public File() {
        super();
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public String getFsrc() {
        return fsrc;
    }

    public void setFsrc(String fsrc) {
        this.fsrc = fsrc == null ? null : fsrc.trim();
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