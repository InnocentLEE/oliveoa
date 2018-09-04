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
    ServerResponse get_overtime_application_approved(String eid);
    ServerResponse get_overtime_application_Isubmit(String eid);
    ServerResponse get_overtime_application_details(String oaid);
    ServerResponse approved_overtime_application(OvertimeApplicationApprovedOpinion overtimeApplicationApprovedOpinion);
    ServerResponse add_leave_application(LeaveApplication leaveApplication, List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList);
    ServerResponse get_leave_application_need_approved(String eid);
    ServerResponse get_leave_application_approved(String eid);
    ServerResponse get_leave_application_Isubmit(String eid);
    ServerResponse get_leave_application_details(String laid);
    ServerResponse approved_leave_application(LeaveApplicationApprovedOpinion leaveApplicationApprovedOpinion);
    ServerResponse add_business_trip_application(BusinessTripApplication businessTripApplication,List<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList);
    ServerResponse get_business_trip_application_need_approved(String eid);
    ServerResponse get_business_trip_application_approved(String eid);
    ServerResponse get_business_trip_application_Isubmit(String eid);
    ServerResponse get_business_trip_application_details(String btaid);
    ServerResponse approved_business_trip_application(BusinessTripApplicationApprovedOpinion businessTripApplicationApprovedOpinion);
    ServerResponse add_job_transfer_application(JobTransferApplication jobTransferApplication,List<JobTransferApplicationApprovedOpinion> jobTransferApplicationApprovedOpinionList);
    ServerResponse get_job_transfer_application_need_approved(String eid);
    ServerResponse get_job_transfer_application_approved(String eid);
    ServerResponse get_job_transfer_application_Isubmit(String eid);
    ServerResponse get_job_transfer_application_details(String jtaid);
    ServerResponse approved_job_transfer_application(JobTransferApplicationApprovedOpinion jobTransferApplicationApprovedOpinion);
    ServerResponse add_leave_office_application(LeaveOfficeApplication leaveOfficeApplication,List<LeaveOfficeApplicationApprovedOpinion> leaveOfficeApplicationApprovedOpinionList);
    ServerResponse get_leave_office_application_need_approved(String eid);
    ServerResponse get_leave_office_application_approved(String eid);
    ServerResponse get_leave_office_application_Isubmit(String eid);
    ServerResponse get_leave_office_application_details(String loaid);
    ServerResponse approved_leave_office_application(LeaveOfficeApplicationApprovedOpinion leaveOfficeApplicationApprovedOpinion);
    ServerResponse add_fulltime_application(FulltimeApplication fulltimeApplication,List<FulltimeApplicationApprovedOpinion> fulltimeApplicationApprovedOpinions);
    ServerResponse get_fulltime_application_need_approved(String eid);
    ServerResponse get_fulltime_application_approved(String eid);
    ServerResponse get_fulltime_application_Isubmit(String eid);
    ServerResponse get_fulltime_application_details(String faid);
    ServerResponse approved_fulltime_application(FulltimeApplicationApprovedOpinion fulltimeApplicationApprovedOpinion);
    ServerResponse add_recruitment_application(RecruitmentApplication recruitmentApplication,RecruitmentApplicationItem recruitmentApplicationItem,List<RecruitmentApplicationApprovedOpinion> recruitmentApplicationApprovedOpinionList);
    ServerResponse get_recruitment_application_need_approved(String eid);
    ServerResponse get_recruitment_application_approved(String eid);
    ServerResponse get_recruitment_application_Isubmit(String eid);
    ServerResponse get_recruitment_application_details(String raid);
    ServerResponse approved_recruitment_application(RecruitmentApplicationApprovedOpinion recruitmentApplicationApprovedOpinion);
}
