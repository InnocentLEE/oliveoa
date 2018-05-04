package com.oliveoa.dao;

import com.oliveoa.pojo.Position;

public interface PositionMapper {
    int deleteByPrimaryKey(String pcid);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(String pcid);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}