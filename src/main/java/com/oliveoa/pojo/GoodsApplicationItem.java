package com.oliveoa.pojo;

import java.util.Date;

public class GoodsApplicationItem {
    private String gaiid;

    private String gaid;

    private String gid;

    private Integer number;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public GoodsApplicationItem(String gaiid, String gaid, String gid, Integer number, Integer orderby, Date createtime, Date updatetime) {
        this.gaiid = gaiid;
        this.gaid = gaid;
        this.gid = gid;
        this.number = number;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public GoodsApplicationItem() {
        super();
    }

    public String getGaiid() {
        return gaiid;
    }

    public void setGaiid(String gaiid) {
        this.gaiid = gaiid == null ? null : gaiid.trim();
    }

    public String getGaid() {
        return gaid;
    }

    public void setGaid(String gaid) {
        this.gaid = gaid == null ? null : gaid.trim();
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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