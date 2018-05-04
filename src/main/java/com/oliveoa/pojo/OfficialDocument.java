package com.oliveoa.pojo;

import java.util.Date;

public class OfficialDocument {
    private String odid;

    private String draftEid;

    private String content;

    private String fid;

    private String title;

    private String nuclearDraftEid;

    private Integer nuclearDraftIsapproved;

    private String nuclearDraftOpinion;

    private String issuedEid;

    private Integer issuedIsapproved;

    private String issuedOpinion;

    private Integer orderby;

    private Date createtime;

    private Date updatetime;

    public OfficialDocument(String odid, String draftEid, String content, String fid, String title, String nuclearDraftEid, Integer nuclearDraftIsapproved, String nuclearDraftOpinion, String issuedEid, Integer issuedIsapproved, String issuedOpinion, Integer orderby, Date createtime, Date updatetime) {
        this.odid = odid;
        this.draftEid = draftEid;
        this.content = content;
        this.fid = fid;
        this.title = title;
        this.nuclearDraftEid = nuclearDraftEid;
        this.nuclearDraftIsapproved = nuclearDraftIsapproved;
        this.nuclearDraftOpinion = nuclearDraftOpinion;
        this.issuedEid = issuedEid;
        this.issuedIsapproved = issuedIsapproved;
        this.issuedOpinion = issuedOpinion;
        this.orderby = orderby;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public OfficialDocument() {
        super();
    }

    public String getOdid() {
        return odid;
    }

    public void setOdid(String odid) {
        this.odid = odid == null ? null : odid.trim();
    }

    public String getDraftEid() {
        return draftEid;
    }

    public void setDraftEid(String draftEid) {
        this.draftEid = draftEid == null ? null : draftEid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getNuclearDraftEid() {
        return nuclearDraftEid;
    }

    public void setNuclearDraftEid(String nuclearDraftEid) {
        this.nuclearDraftEid = nuclearDraftEid == null ? null : nuclearDraftEid.trim();
    }

    public Integer getNuclearDraftIsapproved() {
        return nuclearDraftIsapproved;
    }

    public void setNuclearDraftIsapproved(Integer nuclearDraftIsapproved) {
        this.nuclearDraftIsapproved = nuclearDraftIsapproved;
    }

    public String getNuclearDraftOpinion() {
        return nuclearDraftOpinion;
    }

    public void setNuclearDraftOpinion(String nuclearDraftOpinion) {
        this.nuclearDraftOpinion = nuclearDraftOpinion == null ? null : nuclearDraftOpinion.trim();
    }

    public String getIssuedEid() {
        return issuedEid;
    }

    public void setIssuedEid(String issuedEid) {
        this.issuedEid = issuedEid == null ? null : issuedEid.trim();
    }

    public Integer getIssuedIsapproved() {
        return issuedIsapproved;
    }

    public void setIssuedIsapproved(Integer issuedIsapproved) {
        this.issuedIsapproved = issuedIsapproved;
    }

    public String getIssuedOpinion() {
        return issuedOpinion;
    }

    public void setIssuedOpinion(String issuedOpinion) {
        this.issuedOpinion = issuedOpinion == null ? null : issuedOpinion.trim();
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