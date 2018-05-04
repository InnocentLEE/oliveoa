package com.oliveoa.dao;

import com.oliveoa.pojo.MeetingApplication;

public interface MeetingApplicationMapper {
    int deleteByPrimaryKey(String maid);

    int insert(MeetingApplication record);

    int insertSelective(MeetingApplication record);

    MeetingApplication selectByPrimaryKey(String maid);

    int updateByPrimaryKeySelective(MeetingApplication record);

    int updateByPrimaryKey(MeetingApplication record);
}