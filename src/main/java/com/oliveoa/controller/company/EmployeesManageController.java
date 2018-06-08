package com.oliveoa.controller.company;

import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.CompanyInfo;
import com.oliveoa.pojo.Employees;
import com.oliveoa.service.IEmployeesService;
import com.oliveoa.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Lee on 2018/5/28.
 */
@Controller
@RequestMapping("/manage/employees/")
public class EmployeesManageController {
    @Autowired
    private IEmployeesService iEmployeesService;

    @RequestMapping(value = "add_employee.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_employee(HttpSession session, String id, String dcid, String pcid, String name, String sex, String birth, String tel, String email, String address) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        if (id.equals("") || dcid.equals("") || pcid.equals("") || name.equals("") || sex.equals("") || birth.equals("") || tel.equals("") || email.equals("") || address.equals("")) {
            return ServerResponse.createByErrorMessage("请输入员工全部信息");
        }
        if (!check_id(session, id).isSuccess()) {
            return ServerResponse.createByErrorMessage("ID已被使用");
        }
        Employees employees = new Employees();
        String eid = CommonUtils.uuid();
        employees.setEid(eid);
        employees.setPcid(pcid);
        employees.setDcid(dcid);
        employees.setId(id);
        employees.setName(name);
        employees.setSex(sex);
        employees.setBirth(birth);
        employees.setTel(tel);
        employees.setEmail(email);
        employees.setAddress(address);
        return iEmployeesService.add_employee(employees);
    }

    @RequestMapping(value = "check_id.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse check_id(HttpSession session, String id) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iEmployeesService.check_id(id);
    }

    @RequestMapping(value = "get_employees_by_department.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_employees_by_department(HttpSession session, String dcid) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iEmployeesService.get_employees_by_department(dcid);
    }

    @RequestMapping(value = "get_employees_by_position.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_employees_by_position(HttpSession session, String pcid) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iEmployeesService.get_employees_by_position(pcid);
    }

    @RequestMapping(value = "update_employee.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update_employee(HttpSession session, String eid, String dcid, String pcid, String id, String name, String sex, String birth, String tel, String email, String address) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        if (!check_id(session, id).isSuccess()) {
            return ServerResponse.createByErrorMessage("ID已被使用");
        }
        Employees employees = new Employees();
        employees.setEid(eid);
        if (!dcid.equals("")) employees.setDcid(dcid);
        if (!pcid.equals("")) employees.setPcid(pcid);
        if (!id.equals("")) employees.setId(id);
        if (!name.equals("")) employees.setName(name);
        if (!sex.equals("")) employees.setSex(sex);
        if (!birth.equals("")) employees.setBirth(birth);
        if (!tel.equals("")) employees.setTel(tel);
        if (!email.equals("")) employees.setEmail(email);
        if (!address.equals("")) employees.setAddress(address);
        return iEmployeesService.update_employee(employees);
    }

    @RequestMapping(value = "delete_employee.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delete_employee(HttpSession session,String eid){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iEmployeesService.delete_employee(eid);
    }
}
