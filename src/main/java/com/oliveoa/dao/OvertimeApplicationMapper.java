package com.oliveoa.dao;

import com.oliveoa.pojo.OvertimeApplication;

public interface OvertimeApplicationMapper {
    int deleteByPrimaryKey(String oaid);

    int insert(OvertimeApplication record);

    int insertSelective(OvertimeApplication record);

    OvertimeApplication selectByPrimaryKey(String oaid);

    int updateByPrimaryKeySelective(OvertimeApplication record);

    int updateByPrimaryKey(OvertimeApplication record);
}