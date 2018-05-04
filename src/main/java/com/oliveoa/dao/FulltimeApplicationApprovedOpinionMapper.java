package com.oliveoa.dao;

import com.oliveoa.pojo.FulltimeApplicationApprovedOpinion;

public interface FulltimeApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String faaocid);

    int insert(FulltimeApplicationApprovedOpinion record);

    int insertSelective(FulltimeApplicationApprovedOpinion record);

    FulltimeApplicationApprovedOpinion selectByPrimaryKey(String faaocid);

    int updateByPrimaryKeySelective(FulltimeApplicationApprovedOpinion record);

    int updateByPrimaryKey(FulltimeApplicationApprovedOpinion record);
}