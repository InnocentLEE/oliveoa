package com.oliveoa.dao;

import com.oliveoa.pojo.Empwd;

public interface EmpwdMapper {
    int deleteByPrimaryKey(String id);

    int insert(Empwd record);

    int insertSelective(Empwd record);

    Empwd selectByPrimaryKey(String id);

    int select(Empwd record);

    int updateByPrimaryKeySelective(Empwd record);

    int updateByPrimaryKey(Empwd record);
}