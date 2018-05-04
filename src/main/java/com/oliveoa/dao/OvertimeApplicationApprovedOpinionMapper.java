package com.oliveoa.dao;

import com.oliveoa.pojo.OvertimeApplicationApprovedOpinion;

public interface OvertimeApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String oaaocid);

    int insert(OvertimeApplicationApprovedOpinion record);

    int insertSelective(OvertimeApplicationApprovedOpinion record);

    OvertimeApplicationApprovedOpinion selectByPrimaryKey(String oaaocid);

    int updateByPrimaryKeySelective(OvertimeApplicationApprovedOpinion record);

    int updateByPrimaryKey(OvertimeApplicationApprovedOpinion record);
}