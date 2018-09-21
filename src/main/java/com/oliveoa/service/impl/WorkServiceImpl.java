package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.EmployeesMapper;
import com.oliveoa.dao.IssueWorkMapper;
import com.oliveoa.dao.IssueWorkMemberMapper;
import com.oliveoa.dao.SubmitWorkMapper;
import com.oliveoa.pojo.Employees;
import com.oliveoa.pojo.IssueWork;
import com.oliveoa.pojo.IssueWorkMember;
import com.oliveoa.pojo.SubmitWork;
import com.oliveoa.service.IWorkService;
import com.oliveoa.vo.IssueWorkAndMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2018/6/4.
 */
@Service("iWorkService")
public class WorkServiceImpl implements IWorkService {

    @Autowired
    private SubmitWorkMapper submitWorkMapper;

    @Autowired
    private IssueWorkMapper issueWorkMapper;

    @Autowired
    private IssueWorkMemberMapper issueWorkMemberMapper;

    @Autowired
    private EmployeesMapper employeesMapper;
    @Override
    public ServerResponse submit_work(SubmitWork submitWork) {
        int result = submitWorkMapper.insert(submitWork);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("提交工作成功");
        else
            return ServerResponse.createByErrorMessage("提交工作失败");
    }

    @Override
    public ServerResponse get_work_unapproved(String aeid, int orderBy) {
        List<SubmitWork> submitWorkList = submitWorkMapper.selectUnapprovedByAeid(aeid, orderBy);
        return ServerResponse.createBySuccess("查找成功", submitWorkList);
    }

    @Override
    public ServerResponse get_work_approved(String eid){
        List<SubmitWork> submitWorkList = submitWorkMapper.selectApprovedByAeid(eid);
        return ServerResponse.createBySuccess("查找成功", submitWorkList);
    }

    @Override
    public ServerResponse get_submit_work(String seid, int orderBy) {
        List<SubmitWork> submitWorkList = submitWorkMapper.selectBySeid(seid, orderBy);
        return ServerResponse.createBySuccess("查找成功", submitWorkList);
    }

    @Override
    public ServerResponse get_approved_work(String aeid, int orderBy) {
        List<SubmitWork> submitWorkList = submitWorkMapper.selectByAeid(aeid, orderBy);
        return ServerResponse.createBySuccess("查找成功", submitWorkList);
    }

    @Override
    public ServerResponse get_work_detail(String swid) {
        SubmitWork submitWork = submitWorkMapper.selectByPrimaryKey(swid);
        return ServerResponse.createBySuccess("查找成功", submitWork);
    }

    @Override
    public ServerResponse approved_work(SubmitWork submitWork) {
        int result = submitWorkMapper.updateByPrimaryKeySelective(submitWork);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("批阅成功");
        else
            return ServerResponse.createByErrorMessage("批阅失败");
    }

    @Override
    public ServerResponse issue_work(IssueWork issueWork, ArrayList<IssueWorkMember> issueWorkMemberList) {
        issueWorkMapper.insertSelective(issueWork);
        int memberSize = issueWorkMemberList.size();
        for (int i = 0; i < memberSize; i++) {
            issueWorkMemberMapper.insertSelective(issueWorkMemberList.get(i));
        }
        return ServerResponse.createBySuccessMessage("发布工作成功");
    }

    @Override
    public ServerResponse get_work_IIssue(String eid) {
        List<IssueWork> issueWorkList = issueWorkMapper.selectByEid(eid);
        List<IssueWorkAndMember> issueWorkAndMemberList = new ArrayList<>();
        int issueWorkNumber = issueWorkList.size();
        for (int i = 0; i < issueWorkNumber; i++) {
            IssueWork issueWork = issueWorkList.get(i);
            String iwid = issueWork.getIwid();
            List<IssueWorkMember> issueWorkMemberList = issueWorkMemberMapper.selectByIwid(iwid);
            List<Employees> employeesList = new ArrayList<>();
            int memberNumber = issueWorkMemberList.size();
            for(int j=0;j<memberNumber;j++){
                IssueWorkMember issueWorkMember = issueWorkMemberList.get(j);
                Employees employees = employeesMapper.selectByPrimaryKey(issueWorkMember.getEid());
                employeesList.add(employees);
            }
            IssueWorkAndMember issueWorkAndMember = new IssueWorkAndMember(issueWork, employeesList);
            issueWorkAndMemberList.add(issueWorkAndMember);
        }
        return ServerResponse.createBySuccess("查询成功",issueWorkAndMemberList);
    }

    @Override
    public ServerResponse get_work_tome(String eid, int orderBy){
        List<IssueWork> list = issueWorkMapper.selectByIwmid(eid,orderBy);
        return ServerResponse.createBySuccess(list);
    }
}
