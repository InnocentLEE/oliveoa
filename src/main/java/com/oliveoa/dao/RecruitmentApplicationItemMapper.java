package com.oliveoa.dao;

import com.oliveoa.pojo.RecruitmentApplicationItem;

public interface RecruitmentApplicationItemMapper {
    int deleteByPrimaryKey(String raiid);

    int insert(RecruitmentApplicationItem record);

    int insertSelective(RecruitmentApplicationItem record);

    RecruitmentApplicationItem selectByPrimaryKey(String raiid);

    int updateByPrimaryKeySelective(RecruitmentApplicationItem record);

    int updateByPrimaryKey(RecruitmentApplicationItem record);

    RecruitmentApplicationItem selectByRaid(String raid);
}