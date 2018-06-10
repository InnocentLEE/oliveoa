package com.oliveoa.dao;

import com.oliveoa.pojo.LeaveApplicationApprovedOpinion;
import com.oliveoa.pojo.LeaveOfficeApplicationApprovedOpinion;

import java.util.List;

public interface LeaveApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String laaocid);

    int insert(LeaveApplicationApprovedOpinion record);

    int insertSelective(LeaveApplicationApprovedOpinion record);

    LeaveApplicationApprovedOpinion selectByPrimaryKey(String laaocid);

    int updateByPrimaryKeySelective(LeaveApplicationApprovedOpinion record);

    int updateByPrimaryKey(LeaveApplicationApprovedOpinion record);

    List<LeaveApplicationApprovedOpinion> selectNeedApprovedByEid(String eid);

    List<LeaveApplicationApprovedOpinion> selectByLaid(String laid);

    int updateByLaidAndEid(LeaveApplicationApprovedOpinion leaveApplicationApprovedOpinion);

    int updateIsApprovedToZeroByLaaocid(String laaocid);

    String selectLaaopidByLaidAndEid(LeaveApplicationApprovedOpinion leaveApplicationApprovedOpinion);
}