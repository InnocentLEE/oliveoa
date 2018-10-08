package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.*;
import com.oliveoa.pojo.*;
import com.oliveoa.service.IApplicationService;
import com.oliveoa.util.CommonUtils;
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
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private EmployeesMapper employeesMapper;

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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(overtimeApplicationApprovedOpinionList.get(0).getEid());
        message.setMsg("有一条加班申请正在等待您审核，请尽快处理。");
        messageMapper.insertSelective(message);
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
        String eid1 = overtimeApplicationMapper.selectByPrimaryKey(overtimeApplicationApprovedOpinion.getOaid()).getEid();
        Message message1 = new Message();
        message1.setMid(CommonUtils.uuid());
        message1.setSeid("system_message");
        message1.setEid(eid1);
        message1.setMsg("您有一条加班申请的审核状态已经更新，请注意查看。");
        messageMapper.insertSelective(message1);
        if (overtimeApplicationApprovedOpinion.getIsapproved() == 1) {
            String oaaocid = overtimeApplicationApprovedOpinionMapper.selectOaaopidByOaidAndEid(overtimeApplicationApprovedOpinion);
            if (oaaocid != null) {
                int result2 = overtimeApplicationApprovedOpinionMapper.updateIsApprovedToZeroByOaaocid(oaaocid);
                String eid2 = overtimeApplicationApprovedOpinionMapper.selectByPrimaryKey(oaaocid).getEid();
                Message message2 = new Message();
                message2.setMid(CommonUtils.uuid());
                message2.setSeid("system_message");
                message2.setEid(eid2);
                message2.setMsg("有一条加班申请正在等待您审核，请尽快处理。");
                messageMapper.insertSelective(message2);
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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(leaveApplicationApprovedOpinionList.get(0).getEid());
        message.setMsg("有一条请假申请正在等待您审核，请尽快处理。");
        messageMapper.insertSelective(message);
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
        String eid1 = leaveApplicationMapper.selectByPrimaryKey(leaveApplicationApprovedOpinion.getLaid()).getEid();
        Message message1 = new Message();
        message1.setMid(CommonUtils.uuid());
        message1.setSeid("system_message");
        message1.setEid(eid1);
        message1.setMsg("您有一条请假申请的审核状态已经更新，请注意查看。");
        messageMapper.insertSelective(message1);
        if (leaveApplicationApprovedOpinion.getIsapproved() == 1) {
            String laaocid = leaveApplicationApprovedOpinionMapper.selectLaaopidByLaidAndEid(leaveApplicationApprovedOpinion);
            if (laaocid != null) {
                int result2 = leaveApplicationApprovedOpinionMapper.updateIsApprovedToZeroByLaaocid(laaocid);
                String eid2 = leaveApplicationApprovedOpinionMapper.selectByPrimaryKey(laaocid).getEid();
                Message message2 = new Message();
                message2.setMid(CommonUtils.uuid());
                message2.setSeid("system_message");
                message2.setEid(eid2);
                message2.setMsg("有一条请假申请正在等待您审核，请尽快处理。");
                messageMapper.insertSelective(message2);
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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(businessTripApplicationApprovedOpinionList.get(0).getEid());
        message.setMsg("有一条出差申请正在等待您审核，请尽快处理。");
        messageMapper.insertSelective(message);
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
        String eid1 = businessTripApplicationMapper.selectByPrimaryKey(businessTripApplicationApprovedOpinion.getBtaid()).getEid();
        Message message1 = new Message();
        message1.setMid(CommonUtils.uuid());
        message1.setSeid("system_message");
        message1.setEid(eid1);
        message1.setMsg("您有一条出差申请的审核状态已经更新，请注意查看。");
        messageMapper.insertSelective(message1);
        if (businessTripApplicationApprovedOpinion.getIsapproved() == 1) {
            String btaaocid = businessTripApplicationApprovedOpinionMapper.selectBtaaopidByBtaidAndEid(businessTripApplicationApprovedOpinion);
            if (btaaocid != null) {
                int result2 = businessTripApplicationApprovedOpinionMapper.updateIsApprovedToZeroByBtaaocid(btaaocid);
                String eid2 = businessTripApplicationApprovedOpinionMapper.selectByPrimaryKey(btaaocid).getEid();
                Message message2 = new Message();
                message2.setMid(CommonUtils.uuid());
                message2.setSeid("system_message");
                message2.setEid(eid2);
                message2.setMsg("有一条出差申请正在等待您审核，请尽快处理。");
                messageMapper.insertSelective(message2);
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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(jobTransferApplicationApprovedOpinionList.get(0).getEid());
        message.setMsg("有一条岗位调动申请正在等待您审核，请尽快处理。");
        messageMapper.insertSelective(message);
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
        String eid1 = jobTransferApplicationMapper.selectByPrimaryKey(jobTransferApplicationApprovedOpinion.getJtaid()).getEid();
        Message message1 = new Message();
        message1.setMid(CommonUtils.uuid());
        message1.setSeid("system_message");
        message1.setEid(eid1);
        message1.setMsg("您有一条岗位调动申请的审核状态已经更新，请注意查看。");
        messageMapper.insertSelective(message1);
        if (jobTransferApplicationApprovedOpinion.getIsapproved() == 1) {
            String jtaaocid = jobTransferApplicationApprovedOpinionMapper.selectJtaaopidByBtaidAndEid(jobTransferApplicationApprovedOpinion);
            if (jtaaocid != null) {
                int result2 = jobTransferApplicationApprovedOpinionMapper.updateIsApprovedToZeroByJtaaocid(jtaaocid);
                String eid2 = jobTransferApplicationApprovedOpinionMapper.selectByPrimaryKey(jtaaocid).getEid();
                Message message2 = new Message();
                message2.setMid(CommonUtils.uuid());
                message2.setSeid("system_message");
                message2.setEid(eid2);
                message2.setMsg("有一条岗位调动申请正在等待您审核，请尽快处理。");
                messageMapper.insertSelective(message2);
                if (result2 <= 0)
                    result1 = 0;
            }else{
                JobTransferApplication jobTransferApplication = jobTransferApplicationMapper.selectByPrimaryKey(jobTransferApplicationApprovedOpinion.getJtaid());
                String eid = jobTransferApplication.getEid();
                String aimDcid = jobTransferApplication.getAimdcid();
                String aimPcid = jobTransferApplication.getAimpcid();
                Employees employees = new Employees();
                employees.setEid(eid);
                employees.setDcid(aimDcid);
                employees.setPcid(aimPcid);
                employeesMapper.updateDocumentAndPositionByPrimaryKey(employees);
                Message message3 = new Message();
                message3.setMid(CommonUtils.uuid());
                message3.setSeid("system_message");
                message3.setEid(eid);
                message3.setMsg("您的岗位被调动，请注意查看");
                messageMapper.insertSelective(message3);
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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(leaveOfficeApplicationApprovedOpinionList.get(0).getEid());
        message.setMsg("有一条离职申请正在等待您审核，请尽快处理。");
        messageMapper.insertSelective(message);
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
        String eid1 = leaveOfficeApplicationMapper.selectByPrimaryKey(leaveOfficeApplicationApprovedOpinion.getLoaid()).getEid();
        Message message1 = new Message();
        message1.setMid(CommonUtils.uuid());
        message1.setSeid("system_message");
        message1.setEid(eid1);
        message1.setMsg("您有一条离职申请的审核状态已经更新，请注意查看。");
        messageMapper.insertSelective(message1);
        if(leaveOfficeApplicationApprovedOpinion.getIsapproved()==1){
            String loaaocid = leaveOfficeApplicationApprovedOpinionMapper.selectLoaaopidByLoaidAndEid(leaveOfficeApplicationApprovedOpinion);
            if(loaaocid != null){
                int result2 = leaveOfficeApplicationApprovedOpinionMapper.updateIsApprovedToZeroByLoaaocid(loaaocid);
                String eid2 = leaveOfficeApplicationApprovedOpinionMapper.selectByPrimaryKey(loaaocid).getEid();
                Message message2 = new Message();
                message2.setMid(CommonUtils.uuid());
                message2.setSeid("system_message");
                message2.setEid(eid2);
                message2.setMsg("有一条离职申请正在等待您审核，请尽快处理。");
                messageMapper.insertSelective(message2);
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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(fulltimeApplicationApprovedOpinions.get(0).getEid());
        message.setMsg("有一条转正申请正在等待您审核，请尽快处理。");
        messageMapper.insertSelective(message);
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
        String eid1 = fulltimeApplicationMapper.selectByPrimaryKey(fulltimeApplicationApprovedOpinion.getFaid()).getEid();
        Message message1 = new Message();
        message1.setMid(CommonUtils.uuid());
        message1.setSeid("system_message");
        message1.setEid(eid1);
        message1.setMsg("您有一条转正申请的审核状态已经更新，请注意查看。");
        messageMapper.insertSelective(message1);
        if(fulltimeApplicationApprovedOpinion.getIsapproved() == 1){
            String faaocid = fulltimeApplicationApprovedOpinionMapper.selectFaaopidByFaidAndEid(fulltimeApplicationApprovedOpinion);
            if(faaocid != null){
                int result2 = fulltimeApplicationApprovedOpinionMapper.updateIsApprovedToZeroByFaaocid(faaocid);
                String eid2 = fulltimeApplicationApprovedOpinionMapper.selectByPrimaryKey(faaocid).getEid();
                Message message2 = new Message();
                message2.setMid(CommonUtils.uuid());
                message2.setSeid("system_message");
                message2.setEid(eid2);
                message2.setMsg("有一条转正申请正在等待您审核，请尽快处理。");
                messageMapper.insertSelective(message2);
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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(recruitmentApplicationApprovedOpinionList.get(0).getEid());
        message.setMsg("有一条招聘申请正在等待您审核，请尽快处理。");
        messageMapper.insertSelective(message);
        if (result1 && result2 && result3)
            return ServerResponse.createBySuccessMessage("添加招聘申请成功");
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
        String eid1 = recruitmentApplicationMapper.selectByPrimaryKey(recruitmentApplicationApprovedOpinion.getRaid()).getEid();
        Message message1 = new Message();
        message1.setMid(CommonUtils.uuid());
        message1.setSeid("system_message");
        message1.setEid(eid1);
        message1.setMsg("您有一条招聘申请的审核状态已经更新，请注意查看。");
        messageMapper.insertSelective(message1);
        if(recruitmentApplicationApprovedOpinion.getIsapproved() == 1){
            String raaocid = recruitmentApplicationApprovedOpinionMapper.selectRaaopidByRaidAndEid(recruitmentApplicationApprovedOpinion);
            if(raaocid != null){
                int result2 = recruitmentApplicationApprovedOpinionMapper.updateIsApprovedToZeroByRaaocid(raaocid);
                String eid2 = recruitmentApplicationApprovedOpinionMapper.selectByPrimaryKey(raaocid).getEid();
                Message message2 = new Message();
                message2.setMid(CommonUtils.uuid());
                message2.setSeid("system_message");
                message2.setEid(eid2);
                message2.setMsg("有一条招聘申请正在等待您审核，请尽快处理。");
                messageMapper.insertSelective(message2);
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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(meetingApplication.getAeid());
        message.setMsg("有一条会议申请正在等待您审核，请尽快处理。");
        messageMapper.insertSelective(message);
        if (result1 && result2)
            return ServerResponse.createBySuccessMessage("添加会议申请成功");
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
        if(result){
            String maid = meetingApplication.getMaid();
            List<MeetingMember> meetingMembers = meetingMemberMapper.selectByMaid(maid);
            int size = meetingMembers.size();
            for (int i = 0; i < size; i++) {
                Message message = new Message();
                message.setMid(CommonUtils.uuid());
                message.setSeid("system_message");
                message.setEid(meetingMembers.get(i).getEid());
                message.setMsg("您有一个新会议，请注意查看");
                messageMapper.insertSelective(message);
            }
            return ServerResponse.createBySuccess("审核成功");
        }
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
