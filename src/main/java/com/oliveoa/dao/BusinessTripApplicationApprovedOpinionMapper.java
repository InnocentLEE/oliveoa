package com.oliveoa.dao;

import com.oliveoa.pojo.BusinessTripApplicationApprovedOpinion;

import java.util.List;

public interface BusinessTripApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String btaaocid);

    int insert(BusinessTripApplicationApprovedOpinion record);

    int insertSelective(BusinessTripApplicationApprovedOpinion record);

    BusinessTripApplicationApprovedOpinion selectByPrimaryKey(String btaaocid);

    int updateByPrimaryKeySelective(BusinessTripApplicationApprovedOpinion record);

    int updateByPrimaryKey(BusinessTripApplicationApprovedOpinion record);

    List<BusinessTripApplicationApprovedOpinion> selectNeedApprovedByEid(String eid);

    List<BusinessTripApplicationApprovedOpinion> selectByBtaid(String btaid);

    int updateByBtaidAndEid(BusinessTripApplicationApprovedOpinion businessTripApplicationApprovedOpinion);

    String selectBtaaopidByBtaidAndEid(BusinessTripApplicationApprovedOpinion businessTripApplicationApprovedOpinion);

    int updateIsApprovedToZeroByBtaaocid(String btaaocid);



}