package com.oliveoa.vo;

import com.oliveoa.pojo.FulltimeApplication;
import com.oliveoa.pojo.FulltimeApplicationApprovedOpinion;

import java.util.List;

/**
 * Created by Lee on 2018/9/2.
 */
public class FulltimeApplicationDetails {
    private FulltimeApplication fulltimeApplication;
    private List<FulltimeApplicationApprovedOpinion> fulltimeApplicationApprovedOpinionList;

    public FulltimeApplicationDetails() {
    }

    public FulltimeApplicationDetails(FulltimeApplication fulltimeApplication, List<FulltimeApplicationApprovedOpinion> fulltimeApplicationApprovedOpinionList) {
        this.fulltimeApplication = fulltimeApplication;
        this.fulltimeApplicationApprovedOpinionList = fulltimeApplicationApprovedOpinionList;
    }

    public FulltimeApplication getFulltimeApplication() {
        return fulltimeApplication;
    }

    public void setFulltimeApplication(FulltimeApplication fulltimeApplication) {
        this.fulltimeApplication = fulltimeApplication;
    }

    public List<FulltimeApplicationApprovedOpinion> getFulltimeApplicationApprovedOpinionList() {
        return fulltimeApplicationApprovedOpinionList;
    }

    public void setFulltimeApplicationApprovedOpinionList(List<FulltimeApplicationApprovedOpinion> fulltimeApplicationApprovedOpinionList) {
        this.fulltimeApplicationApprovedOpinionList = fulltimeApplicationApprovedOpinionList;
    }

    @Override
    public String toString() {
        return "FulltimeApplicationDetails{" +
                "fulltimeApplication=" + fulltimeApplication +
                ", fulltimeApplicationApprovedOpinionList=" + fulltimeApplicationApprovedOpinionList +
                '}';
    }
}
