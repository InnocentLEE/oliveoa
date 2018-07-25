package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.*;

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
    ServerResponse add_business_trip_application(BusinessTripApplication businessTripApplication,List<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList);
    ServerResponse get_business_trip_application_need_approved(String eid);
    ServerResponse get_business_trip_application_Isubmit(String eid);
    ServerResponse get_business_trip_application_details(String btaid);
    ServerResponse approved_business_trip_application(BusinessTripApplicationApprovedOpinion businessTripApplicationApprovedOpinion);
}
