package com.oliveoa.dao;

import com.oliveoa.pojo.Announcement;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(String aid);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(String aid);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);
}