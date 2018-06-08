package com.oliveoa.controller.employees;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.Employees;
import com.oliveoa.pojo.IssueWork;
import com.oliveoa.pojo.IssueWorkMember;
import com.oliveoa.pojo.SubmitWork;
import com.oliveoa.service.IWorkService;
import com.oliveoa.util.CommonUtils;
import com.oliveoa.vo.MemberBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lee on 2018/6/2.
 */
@Controller
@RequestMapping("/employee/work/")
public class WorkController {

    @Autowired
    private IWorkService iWorkService;

    @RequestMapping(value = "submit_work.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse submit_work(HttpSession session, String aeid, String content, String begintime, String endtime) {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginTime = null;
        Date endTime = null;
        try {
            beginTime = format.parse(begintime);
            endTime = format.parse(endtime);
        } catch (ParseException e) {
            return ServerResponse.createByErrorMessage("请适用正确的日期格式：yyyy-mm-dd");
        }
        SubmitWork submitWork = new SubmitWork();
        submitWork.setSwid(CommonUtils.uuid());
        submitWork.setSeid(employees.getEid());
        submitWork.setAeid(aeid);
        submitWork.setContent(content);
        submitWork.setBegintime(beginTime);
        submitWork.setEndtime(endTime);
        submitWork.setIsapproved(0);
        return iWorkService.submit_work(submitWork);
    }

    @RequestMapping(value = "get_work_unapproved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_work_unapproved(HttpSession session, String orderBy) {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        Integer orderby = Integer.parseInt(orderBy);
        return iWorkService.get_work_unapproved(employees.getEid(), orderby);
    }

    @RequestMapping(value = "get_submit_work.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_submit_work(HttpSession session, String orderBy) {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        Integer orderby = Integer.parseInt(orderBy);
        return iWorkService.get_submit_work(employees.getEid(), orderby);
    }

    @RequestMapping(value = "get_approved_work.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_approved_work(HttpSession session,String orderBy){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        Integer orderby = Integer.parseInt(orderBy);
        return iWorkService.get_approved_work(employees.getEid(),orderby);
    }

    @RequestMapping(value = "get_work_detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_work_detail(HttpSession session,String swid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iWorkService.get_work_detail(swid);
    }

    @RequestMapping(value = "approved_work.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse approved_work(HttpSession session,String swid,String isApproved,String opinion){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        SubmitWork submitWork = new SubmitWork();
        submitWork.setSwid(swid);
        submitWork.setIsapproved(Integer.parseInt(isApproved));
        submitWork.setOpinion(opinion);
        return iWorkService.approved_work(submitWork);
    }

    @RequestMapping(value = "issue_work.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse issue_work(HttpSession session,String members,String content,String begintime,String endtime){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");

        String iwid = CommonUtils.uuid();
        IssueWork issueWork = new IssueWork();
        issueWork.setIwid(iwid);
        issueWork.setEid(employees.getEid());
        issueWork.setContent(content);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginTime = null;
        Date endTime = null;
        try {
            beginTime = format.parse(begintime);
            endTime = format.parse(endtime);
        } catch (ParseException e) {
            return ServerResponse.createByErrorMessage("请适用正确的日期格式：yyyy-mm-dd");
        }
        issueWork.setBegintime(beginTime);
        issueWork.setEndtime(endTime);

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(members).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<IssueWorkMember> issueWorkMemberList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement member : jsonArray) {
            //使用GSON，直接转成Bean对象
            MemberBean memberBean = gson.fromJson(member, MemberBean.class);
            IssueWorkMember issueWorkMember = new IssueWorkMember();
            issueWorkMember.setIwmid(CommonUtils.uuid());
            issueWorkMember.setIwid(iwid);
            issueWorkMember.setEid(memberBean.getId());
            issueWorkMemberList.add(issueWorkMember);
        }
        return iWorkService.issue_work(issueWork,issueWorkMemberList);
    }

    @RequestMapping(value = "get_work_IIssue.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_work_IIssue(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iWorkService.get_work_IIssue(employees.getEid());
    }
}
