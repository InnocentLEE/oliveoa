package com.oliveoa.dao;

import com.oliveoa.pojo.IssueWorkMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IssueWorkMemberMapper {
    int deleteByPrimaryKey(String iwmid);

    int insert(IssueWorkMember record);

    int insertSelective(IssueWorkMember record);

    IssueWorkMember selectByPrimaryKey(String iwmid);

    int updateByPrimaryKeySelective(IssueWorkMember record);

    int updateByPrimaryKey(IssueWorkMember record);

    List<IssueWorkMember> selectByIwid(String iwid);

}