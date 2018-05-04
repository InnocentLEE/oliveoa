package com.oliveoa.pojo;

import java.util.Date;

public class Position {
    private String pcid;

    private String ppid;

    private String name;

    private String dcid;

    private Integer limit;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public Position(String pcid, String ppid, String name, String dcid, Integer limit, Integer orderby, Date createtime, Date updatetime) {
        this.pcid = pcid;
        this.ppid = ppid;
        this.name = name;
        this.dcid = dcid;
        this.limit = limit;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Position() {
        super();
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid == null ? null : pcid.trim();
    }

    public String getPpid() {
        return ppid;
    }

    public void setPpid(String ppid) {
        this.ppid = ppid == null ? null : ppid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDcid() {
        return dcid;
    }

    public void setDcid(String dcid) {
        this.dcid = dcid == null ? null : dcid.trim();
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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