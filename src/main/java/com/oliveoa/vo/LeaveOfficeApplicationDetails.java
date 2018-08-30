package com.oliveoa.vo;

import com.oliveoa.pojo.LeaveOfficeApplication;
import com.oliveoa.pojo.LeaveOfficeApplicationApprovedOpinion;

import java.util.List;

/**
 * Created by Lee on 2018/8/30.
 */
public class LeaveOfficeApplicationDetails {
    private LeaveOfficeApplication leaveOfficeApplication;
    private List<LeaveOfficeApplicationApprovedOpinion> leaveOfficeApplicationApprovedOpinionList;

    public LeaveOfficeApplicationDetails() {
    }

    public LeaveOfficeApplicationDetails(LeaveOfficeApplication leaveOfficeApplication, List<LeaveOfficeApplicationApprovedOpinion> leaveOfficeApplicationApprovedOpinionList) {
        this.leaveOfficeApplication = leaveOfficeApplication;
        this.leaveOfficeApplicationApprovedOpinionList = leaveOfficeApplicationApprovedOpinionList;
    }

    public LeaveOfficeApplication getLeaveOfficeApplication() {
        return leaveOfficeApplication;
    }

    public void setLeaveOfficeApplication(LeaveOfficeApplication leaveOfficeApplication) {
        this.leaveOfficeApplication = leaveOfficeApplication;
    }

    public List<LeaveOfficeApplicationApprovedOpinion> getLeaveOfficeApplicationApprovedOpinionList() {
        return leaveOfficeApplicationApprovedOpinionList;
    }

    public void setLeaveOfficeApplicationApprovedOpinionList(List<LeaveOfficeApplicationApprovedOpinion> leaveOfficeApplicationApprovedOpinionList) {
        this.leaveOfficeApplicationApprovedOpinionList = leaveOfficeApplicationApprovedOpinionList;
    }

    @Override
    public String toString() {
        return "LeaveOfficeApplicationDetails{" +
                "leaveOfficeApplication=" + leaveOfficeApplication +
                ", leaveOfficeApplicationApprovedOpinionList=" + leaveOfficeApplicationApprovedOpinionList +
                '}';
    }
}
