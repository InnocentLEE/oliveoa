package com.oliveoa.dao;

import com.oliveoa.pojo.RecruitmentApplicationApprovedOpinion;

public interface RecruitmentApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String raaocid);

    int insert(RecruitmentApplicationApprovedOpinion record);

    int insertSelective(RecruitmentApplicationApprovedOpinion record);

    RecruitmentApplicationApprovedOpinion selectByPrimaryKey(String raaocid);

    int updateByPrimaryKeySelective(RecruitmentApplicationApprovedOpinion record);

    int updateByPrimaryKey(RecruitmentApplicationApprovedOpinion record);
}