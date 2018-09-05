package com.oliveoa.dao;

import com.oliveoa.pojo.MeetingApplication;

import java.util.List;

public interface MeetingApplicationMapper {
    int deleteByPrimaryKey(String maid);

    int insert(MeetingApplication record);

    int insertSelective(MeetingApplication record);

    MeetingApplication selectByPrimaryKey(String maid);

    int updateByPrimaryKeySelective(MeetingApplication record);

    int updateByPrimaryKey(MeetingApplication record);

    List<MeetingApplication> selectByAeid(String aeid);

    List<MeetingApplication> selectApprovedByAeid(String aeid);

    List<MeetingApplication> selectByEid(String eid);

    int updateApproved(MeetingApplication record);

    List<MeetingApplication> selectDoingByMemberId(String eid);

    List<MeetingApplication> selectDoneByMemberId(String eid);

    List<MeetingApplication> selectWillByMemberId(String eid);
}