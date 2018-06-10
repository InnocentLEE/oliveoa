package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.LeaveApplicationApprovedOpinionMapper;
import com.oliveoa.dao.LeaveApplicationMapper;
import com.oliveoa.dao.OvertimeApplicationApprovedOpinionMapper;
import com.oliveoa.dao.OvertimeApplicationMapper;
import com.oliveoa.pojo.LeaveApplication;
import com.oliveoa.pojo.LeaveApplicationApprovedOpinion;
import com.oliveoa.pojo.OvertimeApplication;
import com.oliveoa.pojo.OvertimeApplicationApprovedOpinion;
import com.oliveoa.service.IApplicationService;
import com.oliveoa.vo.LeaveApplicationDetails;
import com.oliveoa.vo.OvertimeApplicationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
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
    public ServerResponse get_overtime_application_Isubmit(String eid) {
        List<OvertimeApplication> overtimeApplicationList = overtimeApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess("查询成功", overtimeApplicationList);
    }

    @Override
    public ServerResponse get_overtime_application_details(String oaid) {
        OvertimeApplication overtimeApplication = overtimeApplicationMapper.selectByPrimaryKey(oaid);
        List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList = overtimeApplicationApprovedOpinionMapper.selectByOaid(oaid);
        OvertimeApplicationDetails overtimeApplicationDetails = new OvertimeApplicationDetails(overtimeApplication, overtimeApplicationApprovedOpinionList);
        return ServerResponse.createBySuccess("查询成功", overtimeApplicationDetails);
    }

    @Override
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
    public ServerResponse get_leave_application_Isubmit(String eid) {
        List<LeaveApplication> leaveApplicationList = leaveApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess("查询成功", leaveApplicationList);
    }

    @Override
    public ServerResponse get_leave_application_details(String laid) {
        LeaveApplication leaveApplication = leaveApplicationMapper.selectByPrimaryKey(laid);
        List<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionList = leaveApplicationApprovedOpinionMapper.selectByLaid(laid);
        LeaveApplicationDetails leaveApplicationDetails = new LeaveApplicationDetails(leaveApplication, leaveApplicationApprovedOpinionList);
        return ServerResponse.createBySuccess("查询成功", leaveApplicationDetails);
    }

    @Override
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
}
