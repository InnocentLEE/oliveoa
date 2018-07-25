package com.oliveoa.dao;

import com.oliveoa.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(String mid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(String mid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> selectBySeidAfterOrder(@Param("seid") String seid, @Param("orderBy") int orderBy);

    List<Message> selectByEidAfterOrder(@Param("eid") String eid, @Param("orderBy") int orderBy);
}