package com.oliveoa.dao;

import com.oliveoa.pojo.FulltimeApplication;

public interface FulltimeApplicationMapper {
    int deleteByPrimaryKey(String faid);

    int insert(FulltimeApplication record);

    int insertSelective(FulltimeApplication record);

    FulltimeApplication selectByPrimaryKey(String faid);

    int updateByPrimaryKeySelective(FulltimeApplication record);

    int updateByPrimaryKey(FulltimeApplication record);
}