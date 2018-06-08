package com.oliveoa.dao;

import com.oliveoa.pojo.CompanyInfo;
import org.apache.ibatis.annotations.Param;

public interface CompanyInfoMapper {

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectLogin(@Param("username") String username, @Param("password") String password);

    CompanyInfo selectCompanyInfo(String username);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password, @Param("newPassword") String newPassword);
}