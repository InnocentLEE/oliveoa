package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.OvertimeApplicationApprovedOpinionMapper;
import com.oliveoa.dao.OvertimeApplicationMapper;
import com.oliveoa.pojo.OvertimeApplication;
import com.oliveoa.pojo.OvertimeApplicationApprovedOpinion;
import com.oliveoa.service.IApplicationService;
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
        return ServerResponse.createBySuccess("查询成功",overtimeApplicationList);
    }

    @Override
    public ServerResponse get_overtime_application_Isubmit(String eid){
        List<OvertimeApplication> overtimeApplicationList = overtimeApplicationMapper.selectByEid(eid);
        return ServerResponse.createBySuccess("查询成功",overtimeApplicationList);
    }

    @Override
    public ServerResponse get_overtime_application_details(String oaid){
        OvertimeApplication overtimeApplication = overtimeApplicationMapper.selectByPrimaryKey(oaid);
        List<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionList = overtimeApplicationApprovedOpinionMapper.selectNeedApprovedByOaid(oaid);
        OvertimeApplicationDetails overtimeApplicationDetails = new OvertimeApplicationDetails(overtimeApplication,overtimeApplicationApprovedOpinionList);
        return ServerResponse.createBySuccess("查询成功",overtimeApplicationDetails);
    }

    @Override
    public ServerResponse approved_overtime_application(OvertimeApplicationApprovedOpinion overtimeApplicationApprovedOpinion){
        int result = overtimeApplicationApprovedOpinionMapper.updateByOaidAndEid(overtimeApplicationApprovedOpinion);
        if(result>0)
            return ServerResponse.createBySuccessMessage("审核成功");
        else
            return ServerResponse.createByErrorMessage("审核失败");
    }

}
