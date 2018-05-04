package com.oliveoa.dao;

import com.oliveoa.pojo.IssueWorkMember;

public interface IssueWorkMemberMapper {
    int deleteByPrimaryKey(String iwmid);

    int insert(IssueWorkMember record);

    int insertSelective(IssueWorkMember record);

    IssueWorkMember selectByPrimaryKey(String iwmid);

    int updateByPrimaryKeySelective(IssueWorkMember record);

    int updateByPrimaryKey(IssueWorkMember record);
}