package com.oliveoa.dao;

import com.oliveoa.pojo.GoodsApplicationItem;

public interface GoodsApplicationItemMapper {
    int deleteByPrimaryKey(String gaiid);

    int insert(GoodsApplicationItem record);

    int insertSelective(GoodsApplicationItem record);

    GoodsApplicationItem selectByPrimaryKey(String gaiid);

    int updateByPrimaryKeySelective(GoodsApplicationItem record);

    int updateByPrimaryKey(GoodsApplicationItem record);
}