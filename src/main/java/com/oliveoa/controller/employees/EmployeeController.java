package com.oliveoa.controller.employees;

import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.Employees;
import com.oliveoa.pojo.Empwd;
import com.oliveoa.service.IEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


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
}
