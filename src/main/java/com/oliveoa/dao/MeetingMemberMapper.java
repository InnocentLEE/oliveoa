package com.oliveoa.dao;

import com.oliveoa.pojo.MeetingMember;

public interface MeetingMemberMapper {
    int deleteByPrimaryKey(String maid);

    int insert(MeetingMember record);

    int insertSelective(MeetingMember record);

    MeetingMember selectByPrimaryKey(String maid);

    int updateByPrimaryKeySelective(MeetingMember record);

    int updateByPrimaryKey(MeetingMember record);
}