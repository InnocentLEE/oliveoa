package com.oliveoa.dao;

import com.oliveoa.pojo.LeaveApplicationApprovedOpinion;

public interface LeaveApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String laaocid);

    int insert(LeaveApplicationApprovedOpinion record);

    int insertSelective(LeaveApplicationApprovedOpinion record);

    LeaveApplicationApprovedOpinion selectByPrimaryKey(String laaocid);

    int updateByPrimaryKeySelective(LeaveApplicationApprovedOpinion record);

    int updateByPrimaryKey(LeaveApplicationApprovedOpinion record);
}