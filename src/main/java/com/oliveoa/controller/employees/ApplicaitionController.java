package com.oliveoa.controller.employees;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.Employees;
import com.oliveoa.pojo.OvertimeApplication;
import com.oliveoa.pojo.OvertimeApplicationApprovedOpinion;
import com.oliveoa.service.IApplicationService;
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
 * Created by Lee on 2018/6/7.
 */
@Controller
@RequestMapping("/employee/application/")
public class ApplicaitionController {
    @Autowired
    private IApplicationService iApplicationService;

    @RequestMapping(value = "add_overtime_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_overtime_application(HttpSession session,String reason,String begintime,String endtime,String approvedMember){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime = null;
        Date endTime = null;
        try {
            beginTime = format.parse(begintime);
            endTime = format.parse(endtime);
        } catch (ParseException e) {
            return ServerResponse.createByErrorMessage("请适用正确的日期格式：yyyy-MM-dd HH:mm:ss");
        }
        String oaid = CommonUtils.uuid();
        OvertimeApplication overtimeApplication = new OvertimeApplication();
        overtimeApplication.setOaid(oaid);
        overtimeApplication.setEid(employees.getEid());
        overtimeApplication.setBegintime(beginTime);
        overtimeApplication.setEndtime(endTime);
        overtimeApplication.setReason(reason);

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(approvedMember).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<OvertimeApplicationApprovedOpinion> overtimeApplicationApprovedOpinionArrayList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement member : jsonArray) {
            //使用GSON，直接转成Bean对象
            MemberBean memberBean = gson.fromJson(member, MemberBean.class);
            OvertimeApplicationApprovedOpinion overtimeApplicationApprovedOpinion = new OvertimeApplicationApprovedOpinion();
            overtimeApplicationApprovedOpinion.setOaaopid(null);
            overtimeApplicationApprovedOpinion.setOaaocid(CommonUtils.uuid());
            overtimeApplicationApprovedOpinion.setOaid(oaid);
            overtimeApplicationApprovedOpinion.setEid(memberBean.getId());
            overtimeApplicationApprovedOpinion.setIsapproved(-2);
            overtimeApplicationApprovedOpinionArrayList.add(overtimeApplicationApprovedOpinion);
        }
        //for循环遍历建立审核分级
        int size = overtimeApplicationApprovedOpinionArrayList.size();
        for(int i=0;i<size-1;i++){
            overtimeApplicationApprovedOpinionArrayList.get(i).setOaaopid(overtimeApplicationApprovedOpinionArrayList.get(i+1).getOaaocid());
        }
        overtimeApplicationApprovedOpinionArrayList.get(0).setIsapproved(0);
        return iApplicationService.add_overtime_application(overtimeApplication, overtimeApplicationApprovedOpinionArrayList);
    }

    @RequestMapping(value = "get_overtime_application_need_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_overtime_application_need_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_overtime_application_need_approved(employees.getEid());
    }

    @RequestMapping(value = "get_overtime_application_Isubmit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_overtime_application_Isubmit(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_overtime_application_Isubmit(employees.getEid());
    }

    @RequestMapping(value = "get_overtime_application_details.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_overtime_application_details(HttpSession session ,String oaid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_overtime_application_details(oaid);
    }

    @RequestMapping(value = "approved_overtime_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse approved_overtime_application(HttpSession session,String oaid,String isApproved,String opinion){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int approved = Integer.parseInt(isApproved);
        OvertimeApplicationApprovedOpinion overtimeApplicationApprovedOpinion = new OvertimeApplicationApprovedOpinion();
        overtimeApplicationApprovedOpinion.setIsapproved(approved);
        overtimeApplicationApprovedOpinion.setEid(employees.getEid());
        overtimeApplicationApprovedOpinion.setOaid(oaid);
        overtimeApplicationApprovedOpinion.setOpinion(opinion);
        return iApplicationService.approved_overtime_application(overtimeApplicationApprovedOpinion);
    }

    public ServerResponse add_leave_application(HttpSession session,String begintime,String endtime,String type,String normalrest,String swaprest,String shouldrest,String supplementrest){
        return null;
    }







}
