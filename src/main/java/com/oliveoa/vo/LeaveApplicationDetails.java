package com.oliveoa.vo;

import com.oliveoa.pojo.LeaveApplication;
import com.oliveoa.pojo.LeaveApplicationApprovedOpinion;

import java.util.List;

/**
 * Created by Lee on 2018/6/10.
 */
public class LeaveApplicationDetails {
    private LeaveApplication leaveApplication;
    private List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList;

    public LeaveApplicationDetails() {
    }

    public LeaveApplicationDetails(LeaveApplication leaveApplication, List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList) {
        this.leaveApplication = leaveApplication;
        this.leaveApplicationApprovedOpinionList = leaveApplicationApprovedOpinionList;
    }

    public LeaveApplication getLeaveApplication() {
        return leaveApplication;
    }

    public void setLeaveApplication(LeaveApplication leaveApplication) {
        this.leaveApplication = leaveApplication;
    }

    public List<LeaveApplicationApprovedOpinion> getLeaveApplicationApprovedOpinionList() {
        return leaveApplicationApprovedOpinionList;
    }

    public void setLeaveApplicationApprovedOpinionList(List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList) {
        this.leaveApplicationApprovedOpinionList = leaveApplicationApprovedOpinionList;
    }

    @Override
    public String toString() {
        return "LeaveApplicationDetails{" +
                "leaveApplication=" + leaveApplication +
                ", leaveApplicationApprovedOpinionList=" + leaveApplicationApprovedOpinionList +
                '}';
    }
}
