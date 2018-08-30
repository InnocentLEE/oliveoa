package com.oliveoa.dao;

import com.oliveoa.pojo.AnnouncementApprovedOpinion;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementApprovedOpinionMapper {
    int deleteByPrimaryKey(String aaocid);

    int insert(AnnouncementApprovedOpinion record);

    int insertSelective(AnnouncementApprovedOpinion record);

    AnnouncementApprovedOpinion selectByPrimaryKey(String aaocid);

    int updateByPrimaryKeySelective(AnnouncementApprovedOpinion record);

    int updateByPrimaryKey(AnnouncementApprovedOpinion record);

    int updateByAidAndEid(AnnouncementApprovedOpinion record);

    int updateIsApprovedToZeroByAaocid(@Param("aaocid") String aacoid);

    String selectAaopidByAidAndEid(AnnouncementApprovedOpinion record);

    List<AnnouncementApprovedOpinion> selectByAid(@Param("aid") String aid);
}