package com.oliveoa.dao;

import com.oliveoa.pojo.IssueWork;

public interface IssueWorkMapper {
    int deleteByPrimaryKey(String iwid);

    int insert(IssueWork record);

    int insertSelective(IssueWork record);

    IssueWork selectByPrimaryKey(String iwid);

    int updateByPrimaryKeySelective(IssueWork record);

    int updateByPrimaryKey(IssueWork record);
}