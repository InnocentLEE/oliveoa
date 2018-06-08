package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.CompanyInfo;

/**
 * Created by Lee on 2018/5/7.
 */
public interface ICompanyService {
    ServerResponse<CompanyInfo> login(String username, String password);
    ServerResponse<CompanyInfo> get_companyInfo(String username);
    ServerResponse<CompanyInfo> update_companyInfo(String username,String fullname,String telphone,String fax,String zipcode,String address,String website,String email,String introduction);
    ServerResponse<String> update_password(String username,String password,String newPassword);
}
