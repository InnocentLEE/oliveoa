package com.oliveoa.dao;

import com.oliveoa.pojo.LeaveOfficeApplication;

public interface LeaveOfficeApplicationMapper {
    int deleteByPrimaryKey(String loaid);

    int insert(LeaveOfficeApplication record);

    int insertSelective(LeaveOfficeApplication record);

    LeaveOfficeApplication selectByPrimaryKey(String loaid);

    int updateByPrimaryKeySelective(LeaveOfficeApplication record);

    int updateByPrimaryKey(LeaveOfficeApplication record);
}