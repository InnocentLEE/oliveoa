package com.oliveoa.dao;

import com.oliveoa.pojo.IssueWork;
import com.oliveoa.pojo.IssueWorkMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IssueWorkMapper {
    int deleteByPrimaryKey(String iwid);

    int insert(IssueWork record);

    int insertSelective(IssueWork record);

    IssueWork selectByPrimaryKey(String iwid);

    int updateByPrimaryKeySelective(IssueWork record);

    int updateByPrimaryKey(IssueWork record);

    List<IssueWork> selectByEid(String eid);

    List<IssueWork> selectByIwmid(@Param("eid") String eid, @Param("orderby") Integer orderby);
}