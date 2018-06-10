package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.LeaveApplication;
import com.oliveoa.pojo.LeaveApplicationApprovedOpinion;
import com.oliveoa.pojo.OvertimeApplication;
import com.oliveoa.pojo.OvertimeApplicationApprovedOpinion;

import java.util.List;

/**
 * Created by Lee on 2018/6/7.
 */
public interface IApplicationService {

    ServerResponse add_overtime_application(OvertimeApplication overtimeApplication, List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList);
    ServerResponse get_overtime_application_need_approved(String eid);
    ServerResponse get_overtime_application_Isubmit(String eid);
    ServerResponse get_overtime_application_details(String oaid);
    ServerResponse approved_overtime_application(OvertimeApplicationApprovedOpinion overtimeApplicationApprovedOpinion);
    ServerResponse add_leave_application(LeaveApplication leaveApplication, List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList);
    ServerResponse get_leave_application_need_approved(String eid);
    ServerResponse get_leave_application_Isubmit(String eid);
    ServerResponse get_leave_application_details(String laid);
    ServerResponse approved_leave_application(LeaveApplicationApprovedOpinion leaveApplicationApprovedOpinion);
}
