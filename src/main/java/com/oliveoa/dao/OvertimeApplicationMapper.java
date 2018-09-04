package com.oliveoa.dao;

import com.oliveoa.pojo.OvertimeApplication;

import java.util.List;

public interface OvertimeApplicationMapper {
    int deleteByPrimaryKey(String oaid);

    int insert(OvertimeApplication record);

    int insertSelective(OvertimeApplication record);

    OvertimeApplication selectByPrimaryKey(String oaid);

    int updateByPrimaryKeySelective(OvertimeApplication record);

    int updateByPrimaryKey(OvertimeApplication record);

    List<OvertimeApplication> selectByEid(String eid);

    List<OvertimeApplication> selectApprovedByEid(String eid);
}