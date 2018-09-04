package com.oliveoa.controller.employees;

import com.google.common.collect.EnumMultiset;
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

    @RequestMapping(value = "get_overtime_application_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_overtime_application_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_overtime_application_approved(employees.getEid());
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

    @RequestMapping(value = "get_leave_application_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_leave_application_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_leave_application_approved(employees.getEid());
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

    @RequestMapping(value = "get_business_trip_application_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_business_trip_application_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_business_trip_application_approved(employees.getEid());
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

    @RequestMapping(value = "add_job_transfer_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_job_transfer_application(HttpSession session,String eid,String aimdcid,String aimpcid,String reason,String approvedMember){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        String aeid = employees.getEid();
        String jtaid = CommonUtils.uuid();
        JobTransferApplication jobTransferApplication = new JobTransferApplication();
        jobTransferApplication.setJtaid(jtaid);
        jobTransferApplication.setAeid(aeid);
        jobTransferApplication.setEid(eid);
        jobTransferApplication.setAimdcid(aimdcid);
        jobTransferApplication.setAimpcid(aimpcid);
        jobTransferApplication.setReason(reason);

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(approvedMember).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<JobTransferApplicationApprovedOpinion> jobTransferApplicationApprovedOpinionArrayList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for(JsonElement member:jsonArray){
            //使用GSON，直接转成Bean对象
            MemberBean memberBean = gson.fromJson(member,MemberBean.class);
            JobTransferApplicationApprovedOpinion jobTransferApplicationApprovedOpinion = new JobTransferApplicationApprovedOpinion();
            jobTransferApplicationApprovedOpinion.setJtaaocid(CommonUtils.uuid());
            jobTransferApplicationApprovedOpinion.setJtaaopid(null);
            jobTransferApplicationApprovedOpinion.setJtaid(jtaid);
            jobTransferApplicationApprovedOpinion.setEid(memberBean.getId());
            jobTransferApplicationApprovedOpinion.setIsapproved(-2);
            jobTransferApplicationApprovedOpinionArrayList.add(jobTransferApplicationApprovedOpinion);
        }
        //for循环遍历建立审核分级
        int size = jobTransferApplicationApprovedOpinionArrayList.size();
        for(int i=0;i<size-1;i++){
            jobTransferApplicationApprovedOpinionArrayList.get(i).setJtaaopid(jobTransferApplicationApprovedOpinionArrayList.get(i+1).getJtaaocid());
        }
        jobTransferApplicationApprovedOpinionArrayList.get(0).setIsapproved(0);
        return iApplicationService.add_job_transfer_application(jobTransferApplication,jobTransferApplicationApprovedOpinionArrayList);
    }

    @RequestMapping(value = "get_job_transfer_application_need_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_job_transfer_application_need_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_job_transfer_application_need_approved(employees.getEid());
    }

    @RequestMapping(value = "get_job_transfer_application_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_job_transfer_application_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_job_transfer_application_approved(employees.getEid());
    }

    @RequestMapping(value = "get_job_transfer_application_Isubmit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_job_transfer_application_Isubmit(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_job_transfer_application_Isubmit(employees.getEid());
    }

    @RequestMapping(value = "get_job_transfer_application_details.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_job_transfer_application_details(HttpSession session,String jtaid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_job_transfer_application_details(jtaid);
    }

    @RequestMapping(value = "approved_job_transfer_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse approved_job_transfer_application(HttpSession session,String jtaid,String isApproved,String opinion){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int approved = Integer.parseInt(isApproved);
        JobTransferApplicationApprovedOpinion jobTransferApplicationApprovedOpinion = new JobTransferApplicationApprovedOpinion();
        jobTransferApplicationApprovedOpinion.setJtaid(jtaid);
        jobTransferApplicationApprovedOpinion.setEid(employees.getEid());
        jobTransferApplicationApprovedOpinion.setIsapproved(approved);
        jobTransferApplicationApprovedOpinion.setOpinion(opinion);
        return iApplicationService.approved_job_transfer_application(jobTransferApplicationApprovedOpinion);
    }

    @RequestMapping(value = "add_leave_office_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_leave_office_applicatio(HttpSession session,String leavetime,String handoverMatters,String reason,String approvedMember){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date leaveTime = null;
        try {
            leaveTime = format.parse(leavetime);
        } catch (ParseException e) {
            return ServerResponse.createByErrorMessage("请适用正确的日期格式：yyyy-MM-dd HH:mm:ss");
        }
        String loaid = CommonUtils.uuid();
        LeaveOfficeApplication leaveOfficeApplication = new LeaveOfficeApplication();
        leaveOfficeApplication.setLoaid(loaid);
        leaveOfficeApplication.setEid(employees.getEid());
        leaveOfficeApplication.setLeavetime(leaveTime);
        leaveOfficeApplication.setReason(reason);
        leaveOfficeApplication.setHandoverMatters(handoverMatters);
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(approvedMember).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<LeaveOfficeApplicationApprovedOpinion> leaveOfficeApplicationApprovedOpinionList = new ArrayList<>();
        //加强for循环遍历JsonArray
        for(JsonElement member:jsonArray){
            //使用GSON，直接转成Bean对象]
            MemberBean memberBean = gson.fromJson(member,MemberBean.class);
            LeaveOfficeApplicationApprovedOpinion leaveOfficeApplicationApprovedOpinion = new LeaveOfficeApplicationApprovedOpinion();
            leaveOfficeApplicationApprovedOpinion.setLoaaopid(null);
            leaveOfficeApplicationApprovedOpinion.setLoaaocid(CommonUtils.uuid());
            leaveOfficeApplicationApprovedOpinion.setLoaid(loaid);
            leaveOfficeApplicationApprovedOpinion.setEid(memberBean.getId());
            leaveOfficeApplicationApprovedOpinion.setIsapproved(-2);
            leaveOfficeApplicationApprovedOpinionList.add(leaveOfficeApplicationApprovedOpinion);
        }
        //for循环遍历建立审核分级
        int size = leaveOfficeApplicationApprovedOpinionList.size();
        for(int i=0;i<size-1;i++){
            leaveOfficeApplicationApprovedOpinionList.get(i).setLoaaopid(leaveOfficeApplicationApprovedOpinionList.get(i+1).getLoaaocid());
        }
        leaveOfficeApplicationApprovedOpinionList.get(0).setIsapproved(0);
        return iApplicationService.add_leave_office_application(leaveOfficeApplication,leaveOfficeApplicationApprovedOpinionList);
    }

    @RequestMapping(value = "get_leave_office_application_need_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_leave_office_application_need_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_leave_office_application_need_approved(employees.getEid());
    }

    @RequestMapping(value = "get_leave_office_application_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_leave_office_application_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_leave_office_application_approved(employees.getEid());
    }

    @RequestMapping(value = "get_leave_office_application_Isubmit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_leave_office_application_Isubmit(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_leave_office_application_Isubmit(employees.getEid());
    }

    @RequestMapping(value = "get_leave_office_application_details.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_leave_office_application_details(HttpSession session,String loaid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_leave_office_application_details(loaid);
    }

    @RequestMapping(value = "approved_leave_office_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse approved_leave_office_application(HttpSession session,String loaid,String isApproved,String opinion){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int approved = Integer.parseInt(isApproved);
        LeaveOfficeApplicationApprovedOpinion leaveOfficeApplicationApprovedOpinion = new LeaveOfficeApplicationApprovedOpinion();
        leaveOfficeApplicationApprovedOpinion.setLoaid(loaid);
        leaveOfficeApplicationApprovedOpinion.setEid(employees.getEid());
        leaveOfficeApplicationApprovedOpinion.setIsapproved(approved);
        leaveOfficeApplicationApprovedOpinion.setOpinion(opinion);
        return iApplicationService.approved_leave_office_application(leaveOfficeApplicationApprovedOpinion);
    }

    @RequestMapping(value = "add_fulltime_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_fulltime_application(HttpSession session,String begintime,String endtime,String personalSummary,String approvedMember) {
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
        String faid = CommonUtils.uuid();
        FulltimeApplication fulltimeApplication = new FulltimeApplication();
        fulltimeApplication.setFaid(faid);
        fulltimeApplication.setEid(employees.getEid());
        fulltimeApplication.setBegintime(beginTime);
        fulltimeApplication.setEndtime(endTime);
        fulltimeApplication.setPersonalSummary(personalSummary);
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(approvedMember).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<FulltimeApplicationApprovedOpinion> fulltimeApplicationApprovedOpinionList = new ArrayList<>();
        //加强for循环遍历JsonArray
        for(JsonElement member:jsonArray){
            //使用GSON，直接转成Bean对象]
            MemberBean memberBean = gson.fromJson(member,MemberBean.class);
            FulltimeApplicationApprovedOpinion fulltimeApplicationApprovedOpinion = new FulltimeApplicationApprovedOpinion();
            fulltimeApplicationApprovedOpinion.setFaaopid(null);
            fulltimeApplicationApprovedOpinion.setFaaocid(CommonUtils.uuid());
            fulltimeApplicationApprovedOpinion.setFaid(faid);
            fulltimeApplicationApprovedOpinion.setEid(memberBean.getId());
            fulltimeApplicationApprovedOpinion.setIsapproved(-2);
            fulltimeApplicationApprovedOpinionList.add(fulltimeApplicationApprovedOpinion);
        }
        //for循环遍历建立审核分级
        int size = fulltimeApplicationApprovedOpinionList.size();
        for(int i=0;i<size-1;i++){
            fulltimeApplicationApprovedOpinionList.get(i).setFaaopid(fulltimeApplicationApprovedOpinionList.get(i+1).getFaaocid());
        }
        fulltimeApplicationApprovedOpinionList.get(0).setIsapproved(0);
        return iApplicationService.add_fulltime_application(fulltimeApplication,fulltimeApplicationApprovedOpinionList);
    }

    @RequestMapping(value = "get_fulltime_application_need_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_fulltime_application_need_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_fulltime_application_need_approved(employees.getEid());
    }

    @RequestMapping(value = "get_fulltime_application_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_fulltime_application_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_fulltime_application_approved(employees.getEid());
    }

    @RequestMapping(value = "get_fulltime_application_Isubmit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_fulltime_application_Isubmit(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_fulltime_application_Isubmit(employees.getEid());
    }

    @RequestMapping(value = "get_fulltime_application_details.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_fulltime_application_details(HttpSession session,String faid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_fulltime_application_details(faid);
    }

    @RequestMapping(value = "approved_fulltime_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse approved_fulltime_application(HttpSession session,String faid,String isApproved,String opinion){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int approved = Integer.parseInt(isApproved);
        FulltimeApplicationApprovedOpinion fulltimeApplicationApprovedOpinion = new FulltimeApplicationApprovedOpinion();
        fulltimeApplicationApprovedOpinion.setFaid(faid);
        fulltimeApplicationApprovedOpinion.setEid(employees.getEid());
        fulltimeApplicationApprovedOpinion.setIsapproved(approved);
        fulltimeApplicationApprovedOpinion.setOpinion(opinion);
        return iApplicationService.approved_fulltime_application(fulltimeApplicationApprovedOpinion);
    }

    @RequestMapping(value = "add_recruitment_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_recruitment_application(HttpSession session,String dcid,String pcid,String number,String describe,String request,String salary,String approvedMember) {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        Integer num = Integer.parseInt(number);
        String raid = CommonUtils.uuid();
        RecruitmentApplication recruitmentApplication = new RecruitmentApplication();
        recruitmentApplication.setRaid(raid);
        recruitmentApplication.setEid(employees.getEid());
        recruitmentApplication.setDcid(dcid);
        RecruitmentApplicationItem recruitmentApplicationItem = new RecruitmentApplicationItem();
        recruitmentApplicationItem.setRaiid(CommonUtils.uuid());
        recruitmentApplicationItem.setRaid(raid);
        recruitmentApplicationItem.setNumber(num);
        recruitmentApplicationItem.setPcid(pcid);
        recruitmentApplicationItem.setPositionDescribe(describe);
        recruitmentApplicationItem.setPositionRequest(request);
        recruitmentApplicationItem.setSalary(salary);
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(approvedMember).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<RecruitmentApplicationApprovedOpinion> recruitmentApplicationApprovedOpinionList = new ArrayList<>();
        //加强for循环遍历JsonArray
        for (JsonElement member : jsonArray) {
            //使用GSON，直接转成Bean对象]
            MemberBean memberBean = gson.fromJson(member, MemberBean.class);
            RecruitmentApplicationApprovedOpinion recruitmentApplicationApprovedOpinion = new RecruitmentApplicationApprovedOpinion();
            recruitmentApplicationApprovedOpinion.setRaid(raid);
            recruitmentApplicationApprovedOpinion.setEid(memberBean.getId());
            recruitmentApplicationApprovedOpinion.setRaaocid(CommonUtils.uuid());
            recruitmentApplicationApprovedOpinion.setRaaopid(null);
            recruitmentApplicationApprovedOpinion.setIsapproved(-2);
            recruitmentApplicationApprovedOpinionList.add(recruitmentApplicationApprovedOpinion);
        }
        //for循环遍历建立审核分级
        int size = recruitmentApplicationApprovedOpinionList.size();
        for (int i = 0; i < size - 1; i++) {
            recruitmentApplicationApprovedOpinionList.get(i).setRaaopid(recruitmentApplicationApprovedOpinionList.get(i + 1).getRaaocid());
        }
        recruitmentApplicationApprovedOpinionList.get(0).setIsapproved(0);
        return iApplicationService.add_recruitment_application(recruitmentApplication, recruitmentApplicationItem, recruitmentApplicationApprovedOpinionList);
    }

    @RequestMapping(value = "get_recruitment_application_need_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_recruitment_application_need_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_recruitment_application_need_approved(employees.getEid());
    }

    @RequestMapping(value = "get_recruitment_application_approved.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_recruitment_application_approved(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_recruitment_application_approved(employees.getEid());
    }

    @RequestMapping(value = "get_recruitment_application_Isubmit.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_recruitment_application_Isubmit(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_recruitment_application_Isubmit(employees.getEid());
    }

    @RequestMapping(value = "get_recruitment_application_details.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_recruitment_application_details(HttpSession session,String raid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iApplicationService.get_recruitment_application_details(raid);
    }

    @RequestMapping(value = "approved_recruitment_application.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse approved_recruitment_application(HttpSession session,String raid,String isApproved,String opinion){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int approved = Integer.parseInt(isApproved);
        RecruitmentApplicationApprovedOpinion recruitmentApplicationApprovedOpinion = new RecruitmentApplicationApprovedOpinion();
        recruitmentApplicationApprovedOpinion.setRaid(raid);
        recruitmentApplicationApprovedOpinion.setEid(employees.getEid());
        recruitmentApplicationApprovedOpinion.setIsapproved(approved);
        recruitmentApplicationApprovedOpinion.setOpinion(opinion);
        return iApplicationService.approved_recruitment_application(recruitmentApplicationApprovedOpinion);
    }












}
