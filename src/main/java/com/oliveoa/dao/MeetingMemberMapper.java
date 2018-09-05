package com.oliveoa.dao;

import com.oliveoa.pojo.MeetingMember;

import java.util.List;

public interface MeetingMemberMapper {
    int deleteByPrimaryKey(String maid);

    int insert(MeetingMember record);

    int insertSelective(MeetingMember record);

    List<MeetingMember> selectByMaid(String maid);

    int updateByPrimaryKeySelective(MeetingMember record);

    int updateByPrimaryKey(MeetingMember record);
}