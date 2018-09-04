package com.oliveoa.dao;

import com.oliveoa.pojo.RecruitmentApplicationApprovedOpinion;

import java.util.List;

public interface RecruitmentApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String raaocid);

    int insert(RecruitmentApplicationApprovedOpinion record);

    int insertSelective(RecruitmentApplicationApprovedOpinion record);

    RecruitmentApplicationApprovedOpinion selectByPrimaryKey(String raaocid);

    int updateByPrimaryKeySelective(RecruitmentApplicationApprovedOpinion record);

    int updateByPrimaryKey(RecruitmentApplicationApprovedOpinion record);

    List<RecruitmentApplicationApprovedOpinion> selectByRaid(String raid);

    int updateByRaidAndEid(RecruitmentApplicationApprovedOpinion record);

    String selectRaaopidByRaidAndEid(RecruitmentApplicationApprovedOpinion record);

    int updateIsApprovedToZeroByRaaocid(String raaocid);
}