package com.oliveoa.dao;

import com.oliveoa.pojo.FulltimeApplication;
import com.oliveoa.pojo.FulltimeApplicationApprovedOpinion;

import java.util.List;

public interface FulltimeApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String faaocid);

    int insert(FulltimeApplicationApprovedOpinion record);

    int insertSelective(FulltimeApplicationApprovedOpinion record);

    FulltimeApplicationApprovedOpinion selectByPrimaryKey(String faaocid);

    String selectFaaopidByFaidAndEid(FulltimeApplicationApprovedOpinion record);

    int updateByPrimaryKeySelective(FulltimeApplicationApprovedOpinion record);

    int updateByPrimaryKey(FulltimeApplicationApprovedOpinion record);

    List<FulltimeApplicationApprovedOpinion> selectByFaid(String faid);

    int updateByFaidAndEid(FulltimeApplicationApprovedOpinion record);

    int updateIsApprovedToZeroByFaaocid(String faaocid);
}