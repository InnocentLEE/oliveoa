package com.oliveoa.dao;

import com.oliveoa.pojo.RecruitmentApplication;

public interface RecruitmentApplicationMapper {
    int deleteByPrimaryKey(String raid);

    int insert(RecruitmentApplication record);

    int insertSelective(RecruitmentApplication record);

    RecruitmentApplication selectByPrimaryKey(String raid);

    int updateByPrimaryKeySelective(RecruitmentApplication record);

    int updateByPrimaryKey(RecruitmentApplication record);
}