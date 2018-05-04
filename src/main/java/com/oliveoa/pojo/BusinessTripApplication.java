package com.oliveoa.pojo;

import java.util.Date;

public class BusinessTripApplication {
    private String btaid;

    private String eid;

    private Date begintime;

    private Date endtime;

    private String place;

    private String task;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public BusinessTripApplication(String btaid, String eid, Date begintime, Date endtime, String place, String task, Integer orderby, Date createtime, Date updatetime) {
        this.btaid = btaid;
        this.eid = eid;
        this.begintime = begintime;
        this.endtime = endtime;
        this.place = place;
        this.task = task;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public BusinessTripApplication() {
        super();
    }

    public String getBtaid() {
        return btaid;
    }

    public void setBtaid(String btaid) {
        this.btaid = btaid == null ? null : btaid.trim();
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task == null ? null : task.trim();
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