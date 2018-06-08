package com.oliveoa.vo;

import com.oliveoa.pojo.OvertimeApplication;
import com.oliveoa.pojo.OvertimeApplicationApprovedOpinion;

import java.util.List;

/**
 * Created by Lee on 2018/6/9.
 */
public class OvertimeApplicationDetails {
    private OvertimeApplication overtimeApplication;
    private List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList;

    public OvertimeApplicationDetails(OvertimeApplication overtimeApplication, List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList) {
        this.overtimeApplication = overtimeApplication;
        this.overtimeApplicationApprovedOpinionList = overtimeApplicationApprovedOpinionList;
    }

    public OvertimeApplicationDetails() {
    }

    public OvertimeApplication getOvertimeApplication() {
        return overtimeApplication;
    }

    public void setOvertimeApplication(OvertimeApplication overtimeApplication) {
        this.overtimeApplication = overtimeApplication;
    }

    public List<OvertimeApplicationApprovedOpinion> getOvertimeApplicationApprovedOpinionList() {
        return overtimeApplicationApprovedOpinionList;
    }

    public void setOvertimeApplicationApprovedOpinionList(List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList) {
        this.overtimeApplicationApprovedOpinionList = overtimeApplicationApprovedOpinionList;
    }

    @Override
    public String toString() {
        return "OvertimeApplicationDetails{" +
                "overtimeApplication=" + overtimeApplication +
                ", overtimeApplicationApprovedOpinionList=" + overtimeApplicationApprovedOpinionList +
                '}';
    }
}
