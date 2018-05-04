package com.oliveoa.dao;

import com.oliveoa.pojo.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(String mid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(String mid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}