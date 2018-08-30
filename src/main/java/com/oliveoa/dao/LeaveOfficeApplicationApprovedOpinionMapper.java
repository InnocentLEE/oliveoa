package com.oliveoa.dao;

import com.oliveoa.pojo.LeaveOfficeApplication;
import com.oliveoa.pojo.LeaveOfficeApplicationApprovedOpinion;
import com.sun.tracing.dtrace.ProviderAttributes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveOfficeApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String loaaocid);

    int insert(LeaveOfficeApplicationApprovedOpinion record);

    int insertSelective(LeaveOfficeApplicationApprovedOpinion record);

    LeaveOfficeApplicationApprovedOpinion selectByPrimaryKey(String loaaocid);

    int updateByPrimaryKeySelective(LeaveOfficeApplicationApprovedOpinion record);

    int updateByPrimaryKey(LeaveOfficeApplicationApprovedOpinion record);

    List<LeaveOfficeApplicationApprovedOpinion> selectByLoaid(@Param("loaid") String loaid);

    int updateByLoaidAndEid(LeaveOfficeApplicationApprovedOpinion record);

    String selectLoaaopidByLoaidAndEid(LeaveOfficeApplicationApprovedOpinion record);

    int updateIsApprovedToZeroByLoaaocid(@Param("loaaocid") String loaaocid);
}