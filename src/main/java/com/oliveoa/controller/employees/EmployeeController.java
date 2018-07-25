package com.oliveoa.controller.employees;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.*;
import com.oliveoa.service.IEmployeesService;
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
@RequestMapping("/employee/")
public class EmployeeController {

    @Autowired
    private IEmployeesService iEmployeesService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String id, String password, HttpSession session) {
        Empwd empwd = new Empwd(id, password);
        ServerResponse response = iEmployeesService.login(empwd);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "get_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_info(HttpSession session) {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iEmployeesService.get_info(employees.getEid());
    }

    @RequestMapping(value = "update_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update_info(HttpSession session, String name, String sex, String tel, String birth, String email, String address) {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        Employees employees1 = new Employees();
        employees1.setEid(employees.getEid());
        if (!name.equals(""))
            employees1.setName(name);
        if (!sex.equals(""))
            employees1.setSex(sex);
        if (!tel.equals(""))
            employees1.setTel(tel);
        if (!birth.equals(""))
            employees1.setBirth(birth);
        if (!email.equals(""))
            employees1.setEmail(email);
        if (!address.equals(""))
            employees1.setAddress(address);
        return iEmployeesService.update_info(employees1);
    }

    @RequestMapping(value = "update_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update_password(HttpSession session, String password, String newPassword) {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        Empwd empwd = new Empwd(employees.getId(), password);
        if (iEmployeesService.login(empwd).isSuccess()) {
            empwd.setPwd(newPassword);
            ServerResponse response = iEmployeesService.update_password(empwd);
            if (response.isSuccess()) {
                session.removeAttribute(Const.CURRENT_USER);
            }
            return response;
        } else {
            return ServerResponse.createByErrorMessage("原始密码错误");
        }
    }

    @RequestMapping(value = "get_contact.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_contact(HttpSession session) {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iEmployeesService.get_contact();
    }

    @RequestMapping(value = "sent_message.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse sent_message(HttpSession session,String eid,String msg){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid(employees.getEid());
        message.setEid(eid);
        message.setMsg(msg);
        return iEmployeesService.sent_message(message);
    }

    @RequestMapping(value = "get_message_Isent.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_message_Isent(HttpSession session,String orderBy){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int orderby = Integer.parseInt(orderBy);
        return iEmployeesService.get_message_Isent(employees.getEid(),orderby);
    }

    @RequestMapping(value = "get_message_sent_to_me.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_message_sent_to_me(HttpSession session,String orderBy){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        int orderby = Integer.parseInt(orderBy);
        return iEmployeesService.get_message_sent_to_me(employees.getEid(),orderby);
    }

    @RequestMapping(value = "submit_announcement.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse submit_announcement(HttpSession session,String title,String content,String signature,String publishtime,String approvedMember){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date publishTime = null;
        try {
            publishTime = format.parse(publishtime);
        } catch (ParseException e) {
            return ServerResponse.createByErrorMessage("请适用正确的日期格式：yyyy-MM-dd HH:mm:ss");
        }
        Announcement announcement = new Announcement();
        String aid = CommonUtils.uuid();
        announcement.setAid(aid);
        announcement.setEid(employees.getEid());
        announcement.setTitle(title);
        announcement.setContent(content);
        announcement.setSignature(signature);
        announcement.setPublishtime(publishTime);
        announcement.setIsapproved(0);


        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(approvedMember).getAsJsonArray();

        Gson gson = new Gson();
        ArrayList<AnnouncementApprovedOpinion> announcementApprovedOpinionList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for(JsonElement member:jsonArray){
            //使用GSON，直接转成Bean对象]
            MemberBean memberBean = gson.fromJson(member,MemberBean.class);
            AnnouncementApprovedOpinion announcementApprovedOpinion = new AnnouncementApprovedOpinion();
            announcementApprovedOpinion.setAaocid(CommonUtils.uuid());
            announcementApprovedOpinion.setAid(aid);
            announcementApprovedOpinion.setEid(memberBean.getId());
            announcementApprovedOpinion.setIsapproved(-2);
            announcementApprovedOpinionList.add(announcementApprovedOpinion);
        }
        //for循环遍历建立审核分级
        int size = announcementApprovedOpinionList.size();
        for(int i=0;i<size-1;i++){
            announcementApprovedOpinionList.get(i).setAaopid(announcementApprovedOpinionList.get(i+1).getAaocid());
        }
        announcementApprovedOpinionList.get(0).setIsapproved(0);

        return iEmployeesService.submit_announcement(announcement,announcementApprovedOpinionList);
    }

}
