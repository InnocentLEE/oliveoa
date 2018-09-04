package com.oliveoa.dao;

import com.oliveoa.pojo.FulltimeApplication;

import java.util.List;

public interface FulltimeApplicationMapper {
    int deleteByPrimaryKey(String faid);

    int insert(FulltimeApplication record);

    int insertSelective(FulltimeApplication record);

    FulltimeApplication selectByPrimaryKey(String faid);

    int updateByPrimaryKeySelective(FulltimeApplication record);

    int updateByPrimaryKey(FulltimeApplication record);

    List<FulltimeApplication> selectByApprovedEid(String eid);

    List<FulltimeApplication> selectApprovedByEid(String eid);

    List<FulltimeApplication> selectByEid(String eid);
}