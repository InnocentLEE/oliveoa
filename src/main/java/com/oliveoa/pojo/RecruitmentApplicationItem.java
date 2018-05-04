package com.oliveoa.pojo;

import java.util.Date;

public class RecruitmentApplicationItem {
    private String raiid;

    private String raid;

    private String pcid;

    private Integer number;

    private String positionDescribe;

    private String positionRequest;

    private String salary;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public RecruitmentApplicationItem(String raiid, String raid, String pcid, Integer number, String positionDescribe, String positionRequest, String salary, Integer orderby, Date createtime, Date updatetime) {
        this.raiid = raiid;
        this.raid = raid;
        this.pcid = pcid;
        this.number = number;
        this.positionDescribe = positionDescribe;
        this.positionRequest = positionRequest;
        this.salary = salary;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public RecruitmentApplicationItem() {
        super();
    }

    public String getRaiid() {
        return raiid;
    }

    public void setRaiid(String raiid) {
        this.raiid = raiid == null ? null : raiid.trim();
    }

    public String getRaid() {
        return raid;
    }

    public void setRaid(String raid) {
        this.raid = raid == null ? null : raid.trim();
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid == null ? null : pcid.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPositionDescribe() {
        return positionDescribe;
    }

    public void setPositionDescribe(String positionDescribe) {
        this.positionDescribe = positionDescribe == null ? null : positionDescribe.trim();
    }

    public String getPositionRequest() {
        return positionRequest;
    }

    public void setPositionRequest(String positionRequest) {
        this.positionRequest = positionRequest == null ? null : positionRequest.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
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