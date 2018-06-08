package com.oliveoa.controller.company;

import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.CompanyInfo;
import com.oliveoa.pojo.Department;
import com.oliveoa.service.IDepartmentService;
import com.oliveoa.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Lee on 2018/5/18.
 */
@Controller
@RequestMapping("/manage/department/")
public class DepartmentManageController {
    @Autowired
    private IDepartmentService iDepartmentService;

    @RequestMapping(value = "add_department.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_department(String id, String name, String telephone, String fax, String dpid, HttpSession session) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        if (iDepartmentService.check_departmentID(id).getStatus() != 0) {
            return ServerResponse.createByErrorMessage("id已经存在");
        }
        String dcid = CommonUtils.uuid();
        if (dpid.equals(""))
            dpid = null;
        Department department = new Department();
        department.setDcid(dcid);
        department.setDpid(dpid);
        department.setId(id);
        department.setName(name);
        department.setTelephone(telephone);
        department.setFax(fax);
        ServerResponse response = iDepartmentService.add_department(department);
        return response;
    }

    @RequestMapping(value = "check_id.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse check_departmentID(HttpSession session, String id) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iDepartmentService.check_departmentID(id);

    }

    @RequestMapping(value = "get_departmentByDcid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Department> get_departmentByDcid(HttpSession session, String dcid) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iDepartmentService.get_departmentByDcid(dcid);
    }

    @RequestMapping(value = "get_department.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Department>> get_department(HttpSession session) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iDepartmentService.get_department();
    }

    @RequestMapping(value = "get_children_parallel_department.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Department>> get_children_parallel_department(HttpSession session, String dpid) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        if (dpid.equals(""))
            dpid = null;
        return iDepartmentService.get_children_parallel_department(dpid);
    }
    @RequestMapping(value = "update_department.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update_department(HttpSession session, String dcid, String dpid, String id, String name, String telephone, String fax) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        if(dpid.equals(""))
            dpid = null;
        Department department = new Department();
        department.setDcid(dcid);
        department.setDpid(dpid);
        department.setId(id);
        department.setName(name);
        department.setTelephone(telephone);
        department.setFax(fax);
        return iDepartmentService.update_department(department);
    }

    @RequestMapping(value = "delete_department.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delete_department(HttpSession session,String dcid){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iDepartmentService.delete_department(dcid);
    }
}

