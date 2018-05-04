package com.oliveoa.dao;

import com.oliveoa.pojo.BusinessTripApplicationApprovedOpinion;

public interface BusinessTripApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String btaaocid);

    int insert(BusinessTripApplicationApprovedOpinion record);

    int insertSelective(BusinessTripApplicationApprovedOpinion record);

    BusinessTripApplicationApprovedOpinion selectByPrimaryKey(String btaaocid);

    int updateByPrimaryKeySelective(BusinessTripApplicationApprovedOpinion record);

    int updateByPrimaryKey(BusinessTripApplicationApprovedOpinion record);
}