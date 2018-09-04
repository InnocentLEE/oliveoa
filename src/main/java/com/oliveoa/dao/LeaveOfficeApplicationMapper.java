package com.oliveoa.dao;

import com.oliveoa.pojo.LeaveOfficeApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveOfficeApplicationMapper {
    int deleteByPrimaryKey(String loaid);

    int insert(LeaveOfficeApplication record);

    int insertSelective(LeaveOfficeApplication record);

    LeaveOfficeApplication selectByPrimaryKey(String loaid);

    int updateByPrimaryKeySelective(LeaveOfficeApplication record);

    int updateByPrimaryKey(LeaveOfficeApplication record);

    List<LeaveOfficeApplication> selectByApprovedEid(@Param("eid") String eid);

    List<LeaveOfficeApplication> selectApprovedByEid(@Param("eid") String eid);

    List<LeaveOfficeApplication> selectByEid(@Param("eid") String eid);
}