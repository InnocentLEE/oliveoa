package com.oliveoa.dao;

import com.oliveoa.pojo.RecruitmentApplication;

import java.util.List;

public interface RecruitmentApplicationMapper {
    int deleteByPrimaryKey(String raid);

    int insert(RecruitmentApplication record);

    int insertSelective(RecruitmentApplication record);

    RecruitmentApplication selectByPrimaryKey(String raid);

    int updateByPrimaryKeySelective(RecruitmentApplication record);

    int updateByPrimaryKey(RecruitmentApplication record);

    List<RecruitmentApplication> selectByApprovedEid(String eid);

    List<RecruitmentApplication> selectApprovedByEid(String eid);

    List<RecruitmentApplication> selectByEid(String eid);
}