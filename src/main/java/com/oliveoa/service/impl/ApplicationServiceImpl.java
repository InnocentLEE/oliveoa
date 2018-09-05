package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.*;
import com.oliveoa.pojo.*;
import com.oliveoa.service.IApplicationService;
import com.oliveoa.vo.*;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2018/6/7.
 */
@Service("iApplicationService")
public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    private OvertimeApplicationMapper overtimeApplicationMapper;
    @Autowired
    private OvertimeApplicationApprovedOpinionMapper overtimeApplicationApprovedOpinionMapper;
    @Autowired
    private LeaveApplicationMapper leaveApplicationMapper;
    @Autowired
    private LeaveApplicationApprovedOpinionMapper leaveApplicationApprovedOpinionMapper;
    @Autowired
    private BusinessTripApplicationMapper businessTripApplicationMapper;
    @Autowired
    private BusinessTripApplicationApprovedOpinionMapper businessTripApplicationApprovedOpinionMapper;
    @Autowired
    private JobTransferApplicationMapper jobTransferApplicationMapper;
    @Autowired
    private JobTransferApplicationApprovedOpinionMapper jobTransferApplicationApprovedOpinionMapper;
    @Autowired
    private LeaveOfficeApplicationMapper leaveOfficeApplicationMapper;
    @Autowired
    private LeaveOfficeApplicationApprovedOpinionMapper leaveOfficeApplicationApprovedOpinionMapper;
    @Autowired
    private FulltimeApplicationMapper fulltimeApplicationMapper;
    @Autowired
    private FulltimeApplicationApprovedOpinionMapper fulltimeApplicationApprovedOpinionMapper;
    @Autowired
    private RecruitmentApplicationMapper recruitmentApplicationMapper;
    @Autowired
    private RecruitmentApplicationItemMapper recruitmentApplicationItemMapper;
    @Autowired
    private RecruitmentApplicationApprovedOpinionMapper recruitmentApplicationApprovedOpinionMapper;
    @Autowired
    private MeetingApplicationMapper meetingApplicationMapper;
    @Autowired
    private MeetingMemberMapper meetingMemberMapper;

    @Override
    @Transactional
    public ServerResponse add_overtime_application(OvertimeApplication overtimeApplication, List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList) {
        boolean result1 = overtimeApplicationMapper.insertSelective(overtimeApplication) > 0;
        boolean result2 = true;
        int size = overtimeApplicationApprovedOpinionList.size();
        for (int i = size - 1; i >= 0; i--) {
            int ir = overtimeApplicationApprovedOpinionMapper.insertSelective(overtimeApplicationApprovedOpinionList.get(i));
            if (ir == 0)
                result2 = false;
        }
        if (result1 && result2)
            return ServerResponse.createBySuccessMessage("添加加班申请成功");
        else
            return ServerResponse.createByErrorMessage("添加申请失败");
    }

    @Override
    @Transactional
    public ServerResponse get_overtime_application_need_approved(String eid) {
        List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList = overtimeApplicationApprovedOpinionMapper.selectNeedApprovedByEid(eid);
        int size = overtimeApplicationApprovedOpinionList.size();
        List<OvertimeApplication> overtimeApplicationList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            overtimeApplicationList.add(overtimeApplicationMapper.selectByPrimaryKey(overtimeApplicationApprovedOpinionList.get(i).getOaid()));
        }
        return ServerResponse.createBySuccess("查询成功", overtimeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_overtime_application_approved(String eid){
        List<OvertimeApplication> overtimeApplicationList = overtimeApplicationMapper.selectApprovedByEid(eid);
        return ServerResponse.createBySuccess(overtimeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_overtime_application_Isubmit(String eid) {
        List<OvertimeApplication> overtimeApplicationList = overtimeApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess("查询成功", overtimeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_overtime_application_details(String oaid) {
        OvertimeApplication overtimeApplication = overtimeApplicationMapper.selectByPrimaryKey(oaid);
        List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList = overtimeApplicationApprovedOpinionMapper.selectByOaid(oaid);
        OvertimeApplicationDetails overtimeApplicationDetails = new OvertimeApplicationDetails(overtimeApplication, overtimeApplicationApprovedOpinionList);
        return ServerResponse.createBySuccess("查询成功", overtimeApplicationDetails);
    }

    @Override
    @Transactional
    public ServerResponse approved_overtime_application(OvertimeApplicationApprovedOpinion overtimeApplicationApprovedOpinion) {
        int result1 = overtimeApplicationApprovedOpinionMapper.updateByOaidAndEid(overtimeApplicationApprovedOpinion);
        if (overtimeApplicationApprovedOpinion.getIsapproved() == 1) {
            String oaaocid = overtimeApplicationApprovedOpinionMapper.selectOaaopidByOaidAndEid(overtimeApplicationApprovedOpinion);
            if (oaaocid != null) {
                int result2 = overtimeApplicationApprovedOpinionMapper.updateIsApprovedToZeroByOaaocid(oaaocid);
                if (result2 <= 0)
                    result1 = 0;
            }
        }
        if (result1 > 0)
            return ServerResponse.createBySuccessMessage("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

    @Override
    @Transactional
    public ServerResponse add_leave_application(LeaveApplication leaveApplication, List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList) {
        boolean result1 = leaveApplicationMapper.insertSelective(leaveApplication) > 0;
        boolean result2 = true;
        int size = leaveApplicationApprovedOpinionList.size();
        for (int i = size - 1; i >= 0; i--) {
            int ir = leaveApplicationApprovedOpinionMapper.insertSelective(leaveApplicationApprovedOpinionList.get(i));
            if (ir == 0)
                result2 = false;
        }
        if (result1 && result2)
            return ServerResponse.createBySuccessMessage("添加请假申请成功");
        else
            return ServerResponse.createByErrorMessage("添加请假申请失败");
    }

    @Override
    @Transactional
    public ServerResponse get_leave_application_need_approved(String eid) {
        List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList = leaveApplicationApprovedOpinionMapper.selectNeedApprovedByEid(eid);
        int size = leaveApplicationApprovedOpinionList.size();
        List<LeaveApplication> leaveApplicationList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            leaveApplicationList.add(leaveApplicationMapper.selectByPrimaryKey(leaveApplicationApprovedOpinionList.get(i).getLaid()));
        }
        return ServerResponse.createBySuccess("查询成功", leaveApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_leave_application_approved(String eid){
        List<LeaveApplication> leaveApplicationList = leaveApplicationMapper.selectApprovedByEid(eid);
        return ServerResponse.createBySuccess(leaveApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_leave_application_Isubmit(String eid) {
        List<LeaveApplication> leaveApplicationList = leaveApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess("查询成功", leaveApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_leave_application_details(String laid) {
        LeaveApplication leaveApplication = leaveApplicationMapper.selectByPrimaryKey(laid);
        List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList = leaveApplicationApprovedOpinionMapper.selectByLaid(laid);
        LeaveApplicationDetails leaveApplicationDetails = new LeaveApplicationDetails(leaveApplication, leaveApplicationApprovedOpinionList);
        return ServerResponse.createBySuccess("查询成功", leaveApplicationDetails);
    }

    @Override
    @Transactional
    public ServerResponse approved_leave_application(LeaveApplicationApprovedOpinion leaveApplicationApprovedOpinion) {
        int result1 = leaveApplicationApprovedOpinionMapper.updateByLaidAndEid(leaveApplicationApprovedOpinion);
        if (leaveApplicationApprovedOpinion.getIsapproved() == 1) {
            String laaocid = leaveApplicationApprovedOpinionMapper.selectLaaopidByLaidAndEid(leaveApplicationApprovedOpinion);
            if (laaocid != null) {
                int result2 = leaveApplicationApprovedOpinionMapper.updateIsApprovedToZeroByLaaocid(laaocid);
                if (result2 <= 0)
                    result1 = 0;
            }
        }
        if (result1 > 0)
            return ServerResponse.createBySuccessMessage("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

    @Override
    @Transactional
    public ServerResponse add_business_trip_application(BusinessTripApplication businessTripApplication, List<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList) {
        boolean result1 = businessTripApplicationMapper.insertSelective(businessTripApplication) > 0;
        boolean result2 = true;
        int size = businessTripApplicationApprovedOpinionList.size();
        for (int i = 0; i < size; i++) {
            int ir = businessTripApplicationApprovedOpinionMapper.insertSelective(businessTripApplicationApprovedOpinionList.get(i));
            if (ir == 0)
                result2 = false;
        }
        if (result1 && result2)
            return ServerResponse.createBySuccessMessage("添加出差申请成功");
        else
            return ServerResponse.createByErrorMessage("添加申请失败");
    }

    @Override
    @Transactional
    public ServerResponse get_business_trip_application_need_approved(String eid) {
        List<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList = businessTripApplicationApprovedOpinionMapper.selectNeedApprovedByEid(eid);
        int size = businessTripApplicationApprovedOpinionList.size();
        List<BusinessTripApplication> businessTripApplicationList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            businessTripApplicationList.add(businessTripApplicationMapper.selectByPrimaryKey(businessTripApplicationApprovedOpinionList.get(i).getBtaid()));
        }
        return ServerResponse.createBySuccess("查询成功", businessTripApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_business_trip_application_approved(String eid) {
        List<BusinessTripApplication> businessTripApplicationList = businessTripApplicationMapper.selectApprovedByEid(eid);
        return ServerResponse.createBySuccess(businessTripApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_business_trip_application_Isubmit(String eid) {
        List<BusinessTripApplication> businessTripApplicationList = businessTripApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess("查询成功", businessTripApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_business_trip_application_details(String btaid) {
        BusinessTripApplication businessTripApplication = businessTripApplicationMapper.selectByPrimaryKey(btaid);
        List<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList = businessTripApplicationApprovedOpinionMapper.selectByBtaid(btaid);
        BusinessTripApplicationDetails businessTripApplicationDetails = new BusinessTripApplicationDetails(businessTripApplication, businessTripApplicationApprovedOpinionList);
        return ServerResponse.createBySuccess("查询成功", businessTripApplicationDetails);
    }

    @Override
    @Transactional
    public ServerResponse approved_business_trip_application(BusinessTripApplicationApprovedOpinion businessTripApplicationApprovedOpinion) {
        int result1 = businessTripApplicationApprovedOpinionMapper.updateByBtaidAndEid(businessTripApplicationApprovedOpinion);
        if (businessTripApplicationApprovedOpinion.getIsapproved() == 1) {
            String btaaocid = businessTripApplicationApprovedOpinionMapper.selectBtaaopidByBtaidAndEid(businessTripApplicationApprovedOpinion);
            if (btaaocid != null) {
                int result2 = businessTripApplicationApprovedOpinionMapper.updateIsApprovedToZeroByBtaaocid(btaaocid);
                if (result2 <= 0)
                    result1 = 0;
            }
        }
        if (result1 > 0)
            return ServerResponse.createBySuccessMessage("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

    @Override
    @Transactional
    public ServerResponse add_job_transfer_application(JobTransferApplication jobTransferApplication, List<JobTransferApplicationApprovedOpinion> jobTransferApplicationApprovedOpinionList) {
        boolean result1 = jobTransferApplicationMapper.insertSelective(jobTransferApplication) > 0;
        boolean result2 = true;
        int size = jobTransferApplicationApprovedOpinionList.size();
        for (int i = 0; i < size; i++) {
            int ir = jobTransferApplicationApprovedOpinionMapper.insertSelective(jobTransferApplicationApprovedOpinionList.get(i));
            if (ir == 0)
                result2 = false;
        }
        if (result1 && result2)
            return ServerResponse.createBySuccessMessage("添加岗位调动申请成功");
        else
            return ServerResponse.createByErrorMessage("添加申请失败");
    }

    @Override
    @Transactional
    public ServerResponse get_job_transfer_application_need_approved(String eid) {
        List<JobTransferApplication> jobTransferApplications = jobTransferApplicationMapper.selectByApprovedEid(eid);
        return ServerResponse.createBySuccess(jobTransferApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_job_transfer_application_approved(String eid) {
        List<JobTransferApplication> jobTransferApplications = jobTransferApplicationMapper.selectApprovedByEid(eid);
        return ServerResponse.createBySuccess(jobTransferApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_job_transfer_application_Isubmit(String eid) {
        List<JobTransferApplication> jobTransferApplications = jobTransferApplicationMapper.selectByAEid(eid);
        return ServerResponse.createBySuccess(jobTransferApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_job_transfer_application_details(String jtaid) {
        JobTransferApplication jobTransferApplication = jobTransferApplicationMapper.selectByPrimaryKey(jtaid);
        List<JobTransferApplicationApprovedOpinion> jobTransferApplicationApprovedOpinions = jobTransferApplicationApprovedOpinionMapper.selectByJtaid(jtaid);
        JobTransferApplicationDetails jobTransferApplicationDetails = new JobTransferApplicationDetails(jobTransferApplication, jobTransferApplicationApprovedOpinions);
        return ServerResponse.createBySuccess(jobTransferApplicationDetails);
    }

    @Override
    @Transactional
    public ServerResponse approved_job_transfer_application(JobTransferApplicationApprovedOpinion jobTransferApplicationApprovedOpinion) {
        int result1 = jobTransferApplicationApprovedOpinionMapper.updateByJtaidAndEid(jobTransferApplicationApprovedOpinion);
        if (jobTransferApplicationApprovedOpinion.getIsapproved() == 1) {
            String jtaaocid = jobTransferApplicationApprovedOpinionMapper.selectJtaaopidByBtaidAndEid(jobTransferApplicationApprovedOpinion);
            if (jtaaocid != null) {
                int result2 = jobTransferApplicationApprovedOpinionMapper.updateIsApprovedToZeroByJtaaocid(jtaaocid);
                if (result2 <= 0)
                    result1 = 0;
            }
        }
        if (result1 > 0)
            return ServerResponse.createBySuccessMessage("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

    @Override
    @Transactional
    public ServerResponse add_leave_office_application(LeaveOfficeApplication leaveOfficeApplication,List<LeaveOfficeApplicationApprovedOpinion> leaveOfficeApplicationApprovedOpinionList){
        boolean result1 = leaveOfficeApplicationMapper.insertSelective(leaveOfficeApplication) > 0;
        boolean result2 = true;
        int size = leaveOfficeApplicationApprovedOpinionList.size();
        for(int i=0;i<size;i++){
            int ir = leaveOfficeApplicationApprovedOpinionMapper.insertSelective(leaveOfficeApplicationApprovedOpinionList.get(i));
            if(ir==0)
                result2 = false;
        }
        if (result1 && result2)
            return ServerResponse.createBySuccessMessage("添加离职申请成功");
        else
            return ServerResponse.createByErrorMessage("添加申请失败");
    }

    @Override
    @Transactional
    public ServerResponse get_leave_office_application_need_approved(String eid){
        List<LeaveOfficeApplication> leaveOfficeApplicationList = leaveOfficeApplicationMapper.selectByApprovedEid(eid);
        return ServerResponse.createBySuccess(leaveOfficeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_leave_office_application_approved(String eid){
        List<LeaveOfficeApplication> leaveOfficeApplicationList = leaveOfficeApplicationMapper.selectApprovedByEid(eid);
        return ServerResponse.createBySuccess(leaveOfficeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_leave_office_application_Isubmit(String eid){
        List<LeaveOfficeApplication> leaveOfficeApplicationList = leaveOfficeApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess(leaveOfficeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_leave_office_application_details(String loaid){
        LeaveOfficeApplication leaveOfficeApplication = leaveOfficeApplicationMapper.selectByPrimaryKey(loaid);
        List<LeaveOfficeApplicationApprovedOpinion> leaveOfficeApplicationApprovedOpinions = leaveOfficeApplicationApprovedOpinionMapper.selectByLoaid(loaid);
        LeaveOfficeApplicationDetails leaveOfficeApplicationDetails = new LeaveOfficeApplicationDetails(leaveOfficeApplication,leaveOfficeApplicationApprovedOpinions);
        return ServerResponse.createBySuccess(leaveOfficeApplicationDetails);
    }

    @Override
    @Transactional
    public ServerResponse approved_leave_office_application(LeaveOfficeApplicationApprovedOpinion leaveOfficeApplicationApprovedOpinion){
        int result1 = leaveOfficeApplicationApprovedOpinionMapper.updateByLoaidAndEid(leaveOfficeApplicationApprovedOpinion);
        if(leaveOfficeApplicationApprovedOpinion.getIsapproved()==1){
            String loaaocid = leaveOfficeApplicationApprovedOpinionMapper.selectLoaaopidByLoaidAndEid(leaveOfficeApplicationApprovedOpinion);
            if(loaaocid != null){
                int result2 = leaveOfficeApplicationApprovedOpinionMapper.updateIsApprovedToZeroByLoaaocid(loaaocid);
                if(result2 <= 0)
                    result1 = 0;
            }
        }
        if (result1 > 0)
            return ServerResponse.createBySuccessMessage("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

    @Override
    @Transactional
    public ServerResponse add_fulltime_application(FulltimeApplication fulltimeApplication,List<FulltimeApplicationApprovedOpinion> fulltimeApplicationApprovedOpinions){
        boolean result1 = fulltimeApplicationMapper.insertSelective(fulltimeApplication) > 0;
        boolean result2 = true;
        int size = fulltimeApplicationApprovedOpinions.size();
        for(int i=0;i<size;i++){
            int ir = fulltimeApplicationApprovedOpinionMapper.insertSelective(fulltimeApplicationApprovedOpinions.get(i));
            if(ir == 0)
                result2 = false;
        }
        if (result1 && result2)
            return ServerResponse.createBySuccessMessage("添加转正申请成功");
        else
            return ServerResponse.createByErrorMessage("添加申请失败");
    }

    @Override
    @Transactional
    public ServerResponse get_fulltime_application_need_approved(String eid){
        List<FulltimeApplication> fulltimeApplicationList = fulltimeApplicationMapper.selectByApprovedEid(eid);
        return ServerResponse.createBySuccess(fulltimeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_fulltime_application_approved(String eid){
        List<FulltimeApplication> fulltimeApplicationList = fulltimeApplicationMapper.selectApprovedByEid(eid);
        return ServerResponse.createBySuccess(fulltimeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_fulltime_application_Isubmit(String eid){
        List<FulltimeApplication> fulltimeApplicationList = fulltimeApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess(fulltimeApplicationList);
    }

    @Override
    @Transactional
    public ServerResponse get_fulltime_application_details(String faid){
        FulltimeApplication fulltimeApplication = fulltimeApplicationMapper.selectByPrimaryKey(faid);
        List<FulltimeApplicationApprovedOpinion> fulltimeApplicationApprovedOpinionList = fulltimeApplicationApprovedOpinionMapper.selectByFaid(faid);
        FulltimeApplicationDetails fulltimeApplicationDetails = new FulltimeApplicationDetails(fulltimeApplication,fulltimeApplicationApprovedOpinionList);
        return ServerResponse.createBySuccess(fulltimeApplicationDetails);
    }

    @Override
    @Transactional
    public ServerResponse approved_fulltime_application(FulltimeApplicationApprovedOpinion fulltimeApplicationApprovedOpinion){
        int result1 = fulltimeApplicationApprovedOpinionMapper.updateByFaidAndEid(fulltimeApplicationApprovedOpinion);
        if(fulltimeApplicationApprovedOpinion.getIsapproved() == 1){
            String faaocid = fulltimeApplicationApprovedOpinionMapper.selectFaaopidByFaidAndEid(fulltimeApplicationApprovedOpinion);
            if(faaocid != null){
                int result2 = fulltimeApplicationApprovedOpinionMapper.updateIsApprovedToZeroByFaaocid(faaocid);
                if(result2 <= 0)
                    result1 = 0;
            }
        }
        if (result1 > 0)
            return ServerResponse.createBySuccessMessage("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

    @Override
    @Transactional
    public ServerResponse add_recruitment_application(RecruitmentApplication recruitmentApplication,RecruitmentApplicationItem recruitmentApplicationItem,List<RecruitmentApplicationApprovedOpinion> recruitmentApplicationApprovedOpinionList){
        boolean result1 = recruitmentApplicationMapper.insertSelective(recruitmentApplication) > 0;
        boolean result2 = recruitmentApplicationItemMapper.insertSelective(recruitmentApplicationItem) > 0;
        boolean result3 = true;
        int size = recruitmentApplicationApprovedOpinionList.size();
        for(int i=0;i<size;i++){
            int ir = recruitmentApplicationApprovedOpinionMapper.insertSelective(recruitmentApplicationApprovedOpinionList.get(i));
            if(ir == 0)
                result3 = false;
        }
        if (result1 && result2 && result3)
            return ServerResponse.createBySuccessMessage("添加招聘请成功");
        else
            return ServerResponse.createByErrorMessage("添加申请失败");
    }

    @Override
    @Transactional
    public ServerResponse get_recruitment_application_need_approved(String eid){
        List<RecruitmentApplication> recruitmentApplications = recruitmentApplicationMapper.selectByApprovedEid(eid);
        return ServerResponse.createBySuccess(recruitmentApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_recruitment_application_approved(String eid){
        List<RecruitmentApplication> recruitmentApplications = recruitmentApplicationMapper.selectApprovedByEid(eid);
        return ServerResponse.createBySuccess(recruitmentApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_recruitment_application_Isubmit(String eid){
        List<RecruitmentApplication> recruitmentApplications = recruitmentApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess(recruitmentApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_recruitment_application_details(String raid){
        RecruitmentApplication recruitmentApplication = recruitmentApplicationMapper.selectByPrimaryKey(raid);
        RecruitmentApplicationItem recruitmentApplicationItem = recruitmentApplicationItemMapper.selectByRaid(raid);
        List<RecruitmentApplicationApprovedOpinion> recruitmentApplicationApprovedOpinions = recruitmentApplicationApprovedOpinionMapper.selectByRaid(raid);
        RecruitmentApplicationDetails recruitmentApplicationDetails = new RecruitmentApplicationDetails(recruitmentApplicationItem,recruitmentApplication,recruitmentApplicationApprovedOpinions);
        return ServerResponse.createBySuccess(recruitmentApplicationDetails);
    }

    @Override
    @Transactional
    public ServerResponse approved_recruitment_application(RecruitmentApplicationApprovedOpinion recruitmentApplicationApprovedOpinion){
        int result1 = recruitmentApplicationApprovedOpinionMapper.updateByRaidAndEid(recruitmentApplicationApprovedOpinion);
        if(recruitmentApplicationApprovedOpinion.getIsapproved() == 1){
            String raaocid = recruitmentApplicationApprovedOpinionMapper.selectRaaopidByRaidAndEid(recruitmentApplicationApprovedOpinion);
            if(raaocid != null){
                int result2 = recruitmentApplicationApprovedOpinionMapper.updateIsApprovedToZeroByRaaocid(raaocid);
                if(result2 <= 0)
                    result1 = 0;
            }
        }
        if (result1 > 0)
            return ServerResponse.createBySuccessMessage("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

    @Override
    @Transactional
    public ServerResponse add_meeting_applicatiobn(MeetingApplication meetingApplication,List<MeetingMember> meetingMembers){
        boolean result1 = meetingApplicationMapper.insertSelective(meetingApplication) > 0;
        boolean result2 = true;
        int size = meetingMembers.size();
        for(int i=0;i<size;i++){
            int ir = meetingMemberMapper.insertSelective(meetingMembers.get(i));
            if(ir == 0)
                result2 = false;
        }
        if (result1 && result2)
            return ServerResponse.createBySuccessMessage("添加会议请成功");
        else
            return ServerResponse.createByErrorMessage("添加申请失败");
    }

    @Override
    @Transactional
    public ServerResponse get_meeting_application_need_approved(String aeid){
        List<MeetingApplication> meetingApplications = meetingApplicationMapper.selectByAeid(aeid);
        return ServerResponse.createBySuccess(meetingApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_meeting_application_approved(String aeid){
        List<MeetingApplication> meetingApplications = meetingApplicationMapper.selectApprovedByAeid(aeid);
        return ServerResponse.createBySuccess(meetingApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_meeting_application_Isubmit(String eid){
        List<MeetingApplication> meetingApplications = meetingApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess(meetingApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_meeting_application_details(String maid){
        MeetingApplication meetingApplication = meetingApplicationMapper.selectByPrimaryKey(maid);
        List<MeetingMember> meetingMembers = meetingMemberMapper.selectByMaid(maid);
        MeetingApplicationDetails meetingApplicationDetails = new MeetingApplicationDetails(meetingApplication,meetingMembers);
        return ServerResponse.createBySuccess(meetingApplicationDetails);
    }

    @Override
    @Transactional
    public ServerResponse approved_meeting_application(MeetingApplication meetingApplication){
        boolean result = meetingApplicationMapper.updateApproved(meetingApplication) > 0;
        if(result)
            return ServerResponse.createBySuccess("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

    @Override
    @Transactional
    public ServerResponse get_my_meeting_doing(String eid){
        List<MeetingApplication> meetingApplications = meetingApplicationMapper.selectDoingByMemberId(eid);
        return ServerResponse.createBySuccess(meetingApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_my_meeting_done(String eid){
        List<MeetingApplication> meetingApplications = meetingApplicationMapper.selectDoneByMemberId(eid);
        return ServerResponse.createBySuccess(meetingApplications);
    }

    @Override
    @Transactional
    public ServerResponse get_my_meeting_will_do(String eid){
        List<MeetingApplication> meetingApplications = meetingApplicationMapper.selectWillByMemberId(eid);
        return ServerResponse.createBySuccess(meetingApplications);
    }
}
