package com.oliveoa.controller.company;

import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.CompanyInfo;
import com.oliveoa.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oliveoa.common.Const;

import javax.servlet.http.HttpSession;

/**
 * Created by Lee on 2018/5/7.
 */
@Controller
@RequestMapping("/manage/company/")
public class CompanyManageController {
    @Autowired
    private ICompanyService iCompanyService;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CompanyInfo> login(String username, String password, HttpSession session){
        ServerResponse<CompanyInfo> response = iCompanyService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_COMPANY,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_COMPANY);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "get_companyInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CompanyInfo> get_companyInfo(HttpSession session ){
        CompanyInfo companyInfo = (CompanyInfo)session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,无法获取公司信息");
        }
        ServerResponse<CompanyInfo> response = iCompanyService.get_companyInfo(companyInfo.getUsername());
        return response;
    }

    @RequestMapping(value = "update_companyInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<CompanyInfo> update_companyInfo(HttpSession session,String fullname,String telphone,String fax,String zipcode,String address,String website,String email,String introduction){
        CompanyInfo companyInfo = (CompanyInfo)session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,请先登录");
        }
        ServerResponse<CompanyInfo> response = iCompanyService.update_companyInfo(companyInfo.getUsername(),fullname,telphone,fax,zipcode,address,website,email,introduction);
        return response;
    }
    @RequestMapping(value = "update_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> update_password(HttpSession session,String newPassword){
        CompanyInfo companyInfo = (CompanyInfo)session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,请先登录");
        }
        ServerResponse<String> response = iCompanyService.update_password(companyInfo.getUsername(),companyInfo.getPassword(),newPassword);
        session.removeAttribute(Const.CURRENT_COMPANY);
        return response;
    }

}
