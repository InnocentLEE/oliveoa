package com.oliveoa.pojo;

import java.util.Date;

public class GoodsBorrowRecord {
    private String rid;

    private String eid;

    private String gid;

    private Integer number;

    private Date borrowtime;

    private Date returntime;

    private Date limittime;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public GoodsBorrowRecord(String rid, String eid, String gid, Integer number, Date borrowtime, Date returntime, Date limittime, Integer orderby, Date createtime, Date updatetime) {
        this.rid = rid;
        this.eid = eid;
        this.gid = gid;
        this.number = number;
        this.borrowtime = borrowtime;
        this.returntime = returntime;
        this.limittime = limittime;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public GoodsBorrowRecord() {
        super();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
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

    public Date getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(Date borrowtime) {
        this.borrowtime = borrowtime;
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public Date getLimittime() {
        return limittime;
    }

    public void setLimittime(Date limittime) {
        this.limittime = limittime;
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