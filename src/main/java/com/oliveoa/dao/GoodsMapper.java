package com.oliveoa.dao;

import com.oliveoa.pojo.Goods;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(String gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String gid);

    List<Goods> select();

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int selectCountByPcid(String pcid);

    int selectTotal(String gid);

    int selectRemaining(String gid);
}