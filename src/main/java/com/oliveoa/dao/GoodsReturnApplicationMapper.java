package com.oliveoa.dao;

import com.oliveoa.pojo.GoodsReturnApplication;

public interface GoodsReturnApplicationMapper {
    int deleteByPrimaryKey(String gaiid);

    int insert(GoodsReturnApplication record);

    int insertSelective(GoodsReturnApplication record);

    GoodsReturnApplication selectByPrimaryKey(String gaiid);

    int updateByPrimaryKeySelective(GoodsReturnApplication record);

    int updateByPrimaryKey(GoodsReturnApplication record);
}