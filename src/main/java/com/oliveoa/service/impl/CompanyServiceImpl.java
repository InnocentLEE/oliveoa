package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.CompanyInfoMapper;
import com.oliveoa.pojo.CompanyInfo;
import com.oliveoa.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lee on 2018/5/7.
 */
@Service("iCompanyService")
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;


    @Override
    public ServerResponse<CompanyInfo> login(String username, String password) {
        CompanyInfo companyInfo = companyInfoMapper.selectLogin(username,password);
        if (companyInfo == null){
            return ServerResponse.createByErrorMessage("账号或密码错误");
        }
        return ServerResponse.createBySuccess("登录成功",companyInfo);
    }
    @Override
    public ServerResponse<CompanyInfo> get_companyInfo(String username){
        CompanyInfo companyInfo = companyInfoMapper.selectCompanyInfo(username);
        return ServerResponse.createBySuccess("查询成功",companyInfo);
    }

    @Override
    public ServerResponse<CompanyInfo> update_companyInfo(String username,String fullname,String telphone,String fax,String zipcode,String address,String website,String email,String introduction){
        CompanyInfo updateCompanyInfo = new CompanyInfo();
        updateCompanyInfo.setUsername(username);
        updateCompanyInfo.setFullname(fullname);
        updateCompanyInfo.setTelephone(telphone);
        updateCompanyInfo.setFax(fax);
        updateCompanyInfo.setZipcode(zipcode);
        updateCompanyInfo.setAddress(address);
        updateCompanyInfo.setWebsite(website);
        updateCompanyInfo.setEmail(email);
        updateCompanyInfo.setIntroduction(introduction);
        companyInfoMapper.updateByPrimaryKeySelective(updateCompanyInfo);
        return ServerResponse.createBySuccessMessage("更新成功");
    }
    @Override
    public ServerResponse<String> update_password(String username,String password,String newPassword){
        companyInfoMapper.updatePasswordByUsername(username, password, newPassword);
        return ServerResponse.createBySuccessMessage("密码修改成功，请重新登录");
    }
}
