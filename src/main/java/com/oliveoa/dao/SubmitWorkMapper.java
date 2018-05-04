package com.oliveoa.dao;

import com.oliveoa.pojo.SubmitWork;

public interface SubmitWorkMapper {
    int deleteByPrimaryKey(String swid);

    int insert(SubmitWork record);

    int insertSelective(SubmitWork record);

    SubmitWork selectByPrimaryKey(String swid);

    int updateByPrimaryKeySelective(SubmitWork record);

    int updateByPrimaryKey(SubmitWork record);
}