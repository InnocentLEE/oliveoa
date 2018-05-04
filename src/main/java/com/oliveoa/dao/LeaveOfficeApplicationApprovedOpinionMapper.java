package com.oliveoa.dao;

import com.oliveoa.pojo.LeaveOfficeApplicationApprovedOpinion;

public interface LeaveOfficeApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String loaaocid);

    int insert(LeaveOfficeApplicationApprovedOpinion record);

    int insertSelective(LeaveOfficeApplicationApprovedOpinion record);

    LeaveOfficeApplicationApprovedOpinion selectByPrimaryKey(String loaaocid);

    int updateByPrimaryKeySelective(LeaveOfficeApplicationApprovedOpinion record);

    int updateByPrimaryKey(LeaveOfficeApplicationApprovedOpinion record);
}