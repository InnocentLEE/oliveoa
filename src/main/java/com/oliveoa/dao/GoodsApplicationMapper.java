package com.oliveoa.dao;

import com.oliveoa.pojo.GoodsApplication;

public interface GoodsApplicationMapper {
    int deleteByPrimaryKey(String gaid);

    int insert(GoodsApplication record);

    int insertSelective(GoodsApplication record);

    GoodsApplication selectByPrimaryKey(String gaid);

    int updateByPrimaryKeySelective(GoodsApplication record);

    int updateByPrimaryKey(GoodsApplication record);
}