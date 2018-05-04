package com.oliveoa.dao;

import com.oliveoa.pojo.LeaveApplication;

public interface LeaveApplicationMapper {
    int deleteByPrimaryKey(String laid);

    int insert(LeaveApplication record);

    int insertSelective(LeaveApplication record);

    LeaveApplication selectByPrimaryKey(String laid);

    int updateByPrimaryKeySelective(LeaveApplication record);

    int updateByPrimaryKey(LeaveApplication record);
}