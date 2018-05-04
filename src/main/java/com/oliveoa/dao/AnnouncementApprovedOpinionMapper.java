package com.oliveoa.dao;

import com.oliveoa.pojo.AnnouncementApprovedOpinion;

public interface AnnouncementApprovedOpinionMapper {
    int deleteByPrimaryKey(String aaocid);

    int insert(AnnouncementApprovedOpinion record);

    int insertSelective(AnnouncementApprovedOpinion record);

    AnnouncementApprovedOpinion selectByPrimaryKey(String aaocid);

    int updateByPrimaryKeySelective(AnnouncementApprovedOpinion record);

    int updateByPrimaryKey(AnnouncementApprovedOpinion record);
}