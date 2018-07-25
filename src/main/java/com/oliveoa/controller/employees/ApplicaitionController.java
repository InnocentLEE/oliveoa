package com.oliveoa.controller.employees;

import com.google.common.collect.Table;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.*;
import com.oliveoa.service.IApplicationService;
import com.oliveoa.util.CommonUtils;
import com.oliveoa.vo.MemberBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
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

    @RequestMapping(value = "add_leave_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_leave_application(HttpSession session,String begintime,String endtime,String reason,String type,String normalrest,String swaprest,String shouldrest,String supplementrest,String approvedMember){
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
        int type1 = Integer.parseInt(type);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date normalRest = null;
        Date swapRest = null;
        Date shouldRest = null;
        Date supplementRest = null;
        try {
            if(!normalrest.equals(""))
            normalRest = format1.parse(normalrest);
            if(!swaprest.equals(""))
            swapRest = format1.parse(swaprest);
            if(!shouldrest.equals(""))
            shouldRest = format1.parse(shouldrest);
            if(!supplementrest.equals(""))
            supplementRest = format1.parse(supplementrest);
        } catch (ParseException e) {
            return ServerResponse.createByErrorMessage("请适用正确的正休、串休，应休，补休日期格式：yyyy-MM-dd");
        }
        String laid = CommonUtils.uuid();
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setLaid(laid);
        leaveApplication.setEid(employees.getEid());
        leaveApplication.setBegintime(beginTime);
        leaveApplication.setEndtime(endTime);
        leaveApplication.setType(type1);
        leaveApplication.setReason(reason);
        leaveApplication.setNormalRest(normalRest);
        leaveApplication.setSwapRest(swapRest);
        leaveApplication.setShouldRest(shouldRest);
        leaveApplication.setSupplementRest(supplementRest);

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(approvedMember).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<LeaveApplicationApprovedOpinion> leaveApplicationApprovedOpinionArrayList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement member : jsonArray) {
            //使用GSON，直接转成Bean对象
            MemberBean memberBean = gson.fromJson(member, MemberBean.class);
            LeaveApplicationApprovedOpinion leaveApplicationApprovedOpinion = new LeaveApplicationApprovedOpinion();
            leaveApplicationApprovedOpinion.setLaaopid(null);
            leaveApplicationApprovedOpinion.setLaaocid(CommonUtils.uuid());
            leaveApplicationApprovedOpinion.setLaid(laid);
            leaveApplicationApprovedOpinion.setEid(memberBean.getId());
            leaveApplicationApprovedOpinion.setIsapproved(-2);
            leaveApplicationApprovedOpinionArrayList.add(leaveApplicationApprovedOpinion);
        }
        //for循环遍历建立审核分级
        int size = leaveApplicationApprovedOpinionArrayList.size();
        for(int i=0;i<size-1;i++){
            leaveApplicationApprovedOpinionArrayList.get(i).setLaaopid(leaveApplicationApprovedOpinionArrayList.get(i+1).getLaaocid());
        }
        leaveApplicationApprovedOpinionArrayList.get(0).setIsapproved(0);
        return iApplicationService.add_leave_application(leaveApplication,leaveApplicationApprovedOpinionArrayList);
    }

    @RequestMapping(value = "get_leave_application_need_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_leave_application_need_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_leave_application_need_approved(employees.getEid());
    }

    @RequestMapping(value = "get_leave_application_Isubmit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_leave_application_Isubmit(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_leave_application_Isubmit(employees.getEid());
    }

    @RequestMapping(value = "get_leave_application_details.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_leave_application_details(HttpSession session,String laid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_leave_application_details(laid);
    }

    @RequestMapping(value = "approved_leave_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse approved_leave_application(HttpSession session,String laid,String isApproved,String opinion){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int approved = Integer.parseInt(isApproved);
        LeaveApplicationApprovedOpinion leaveApplicationApprovedOpinion = new LeaveApplicationApprovedOpinion();
        leaveApplicationApprovedOpinion.setEid(employees.getEid());
        leaveApplicationApprovedOpinion.setOpinion(opinion);
        leaveApplicationApprovedOpinion.setIsapproved(approved);
        leaveApplicationApprovedOpinion.setLaid(laid);
        return iApplicationService.approved_leave_application(leaveApplicationApprovedOpinion);
    }

    @RequestMapping(value = "add_business_trip_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_business_trip_application(HttpSession session,String begintime,String endtime,String place,String task,String approvedMember){
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
        String btaid = CommonUtils.uuid();
        BusinessTripApplication businessTripApplication = new BusinessTripApplication();
        businessTripApplication.setBtaid(btaid);
        businessTripApplication.setEid(employees.getEid());
        businessTripApplication.setBegintime(beginTime);
        businessTripApplication.setEndtime(endTime);
        businessTripApplication.setPlace(place);
        businessTripApplication.setTask(task);

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(approvedMember).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for(JsonElement member:jsonArray){
            //使用GSON，直接转成Bean对象]
            MemberBean memberBean = gson.fromJson(member,MemberBean.class);
            BusinessTripApplicationApprovedOpinion businessTripApplicationApprovedOpinion = new BusinessTripApplicationApprovedOpinion();
            businessTripApplicationApprovedOpinion.setBtaaopid(null);
            businessTripApplicationApprovedOpinion.setBtaaocid(CommonUtils.uuid());
            businessTripApplicationApprovedOpinion.setBtaid(btaid);
            businessTripApplicationApprovedOpinion.setEid(memberBean.getId());
            businessTripApplicationApprovedOpinion.setIsapproved(-2);
            businessTripApplicationApprovedOpinionList.add(businessTripApplicationApprovedOpinion);
        }
        //for循环遍历建立审核分级
        int size = businessTripApplicationApprovedOpinionList.size();
        for(int i=0;i<size-1;i++){
            businessTripApplicationApprovedOpinionList.get(i).setBtaaopid(businessTripApplicationApprovedOpinionList.get(i+1).getBtaaocid());
        }
        businessTripApplicationApprovedOpinionList.get(0).setIsapproved(0);
        return iApplicationService.add_business_trip_application(businessTripApplication,businessTripApplicationApprovedOpinionList);
    }

    @RequestMapping(value = "get_business_trip_application_need_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_business_trip_application_need_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_business_trip_application_need_approved(employees.getEid());
    }

    @RequestMapping(value = "get_business_trip_application_Isubmit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_business_trip_application_Isubmit(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_business_trip_application_Isubmit(employees.getEid());
    }

    @RequestMapping(value = "get_business_trip_application_details.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_business_trip_application_details(HttpSession session ,String btaid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_business_trip_application_details(btaid);
    }

    @RequestMapping(value = "approved_business_trip_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse approved_business_trip_application(HttpSession session,String btaid,String isApproved,String opinion){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int approved = Integer.parseInt(isApproved);
        BusinessTripApplicationApprovedOpinion businessTripApplicationApprovedOpinion = new BusinessTripApplicationApprovedOpinion();
        businessTripApplicationApprovedOpinion.setBtaid(btaid);
        businessTripApplicationApprovedOpinion.setEid(employees.getEid());
        businessTripApplicationApprovedOpinion.setIsapproved(approved);
        businessTripApplicationApprovedOpinion.setOpinion(opinion);
        return iApplicationService.approved_business_trip_application(businessTripApplicationApprovedOpinion);
    }








}
