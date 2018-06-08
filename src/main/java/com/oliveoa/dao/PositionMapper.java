package com.oliveoa.dao;

import com.oliveoa.pojo.Position;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(String pcid);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(String pcid);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> selectByDcid(String dcid);

    int selectCountByPpid(String ppid);

    int selectCountByDcid(String dcid);
}