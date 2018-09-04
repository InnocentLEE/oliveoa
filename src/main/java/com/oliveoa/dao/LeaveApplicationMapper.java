package com.oliveoa.dao;

import com.oliveoa.pojo.LeaveApplication;

import java.util.List;

public interface LeaveApplicationMapper {
    int deleteByPrimaryKey(String laid);

    int insert(LeaveApplication record);

    int insertSelective(LeaveApplication record);

    LeaveApplication selectByPrimaryKey(String laid);

    int updateByPrimaryKeySelective(LeaveApplication record);

    int updateByPrimaryKey(LeaveApplication record);

    List<LeaveApplication> selectByEid(String eid);

    List<LeaveApplication> selectApprovedByEid(String eid);
}