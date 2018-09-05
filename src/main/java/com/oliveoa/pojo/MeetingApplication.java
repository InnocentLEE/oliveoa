package com.oliveoa.pojo;

import java.util.Date;

public class MeetingApplication {
    private String maid;

    private String eid;

    private String aeid;

    private String theme;

    private Date begintime;

    private Date endtime;

    private String place;

    private Integer isapproved;

    private String opinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;


    public MeetingApplication() {
        super();
    }

    public MeetingApplication(String maid, String eid, String aeid, String theme, Date begintime, Date endtime, String place, Integer isapproved, String opinion, Integer orderby, Date updatetime, Date createtime) {
        this.maid = maid;
        this.eid = eid;
        this.aeid = aeid;
        this.theme = theme;
        this.begintime = begintime;
        this.endtime = endtime;
        this.place = place;
        this.isapproved = isapproved;
        this.opinion = opinion;
        this.orderby = orderby;
        this.updatetime = updatetime;
        this.createtime = createtime;
    }

    public String getMaid() {
        return maid;
    }

    public void setMaid(String maid) {
        this.maid = maid;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getAeid() {
        return aeid;
    }

    public void setAeid(String aeid) {
        this.aeid = aeid;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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
        this.place = place;
    }

    public Integer getIsapproved() {
        return isapproved;
    }

    public void setIsapproved(Integer isapproved) {
        this.isapproved = isapproved;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "MeetingApplication{" +
                "maid='" + maid + '\'' +
                ", eid='" + eid + '\'' +
                ", aeid='" + aeid + '\'' +
                ", theme='" + theme + '\'' +
                ", begintime=" + begintime +
                ", endtime=" + endtime +
                ", place='" + place + '\'' +
                ", isapproved=" + isapproved +
                ", opinion='" + opinion + '\'' +
                ", orderby=" + orderby +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}