package com.oliveoa.dao;

import com.oliveoa.pojo.Announcement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(String aid);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(String aid);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);

    int updateisApprovedByAid(@Param("aid") String aid, @Param("isApproved") int isApproved);

    List<Announcement> selectByApprovedEid(String eid);

    List<Announcement> selectApprovedByEid(String eid);

    List<Announcement> selectByEid(String eid);

    List<Announcement> selectPublished();

}