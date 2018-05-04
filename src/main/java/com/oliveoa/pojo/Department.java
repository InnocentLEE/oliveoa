package com.oliveoa.pojo;

import java.util.Date;

public class Department {
    private String dcid;

    private String dpid;

    private String id;

    private String name;

    private String telephone;

    private String fax;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public Department(String dcid, String dpid, String id, String name, String telephone, String fax, Integer orderby, Date createtime, Date updatetime) {
        this.dcid = dcid;
        this.dpid = dpid;
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.fax = fax;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Department() {
        super();
    }

    public String getDcid() {
        return dcid;
    }

    public void setDcid(String dcid) {
        this.dcid = dcid == null ? null : dcid.trim();
    }

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        this.dpid = dpid == null ? null : dpid.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
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