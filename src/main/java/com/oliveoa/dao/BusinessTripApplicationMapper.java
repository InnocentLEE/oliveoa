package com.oliveoa.dao;

import com.oliveoa.pojo.BusinessTripApplication;

public interface BusinessTripApplicationMapper {
    int deleteByPrimaryKey(String btaid);

    int insert(BusinessTripApplication record);

    int insertSelective(BusinessTripApplication record);

    BusinessTripApplication selectByPrimaryKey(String btaid);

    int updateByPrimaryKeySelective(BusinessTripApplication record);

    int updateByPrimaryKey(BusinessTripApplication record);
}