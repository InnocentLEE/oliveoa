package com.oliveoa.dao;

import com.oliveoa.pojo.CompantInfo;

public interface CompantInfoMapper {
    int insert(CompantInfo record);

    int insertSelective(CompantInfo record);
}