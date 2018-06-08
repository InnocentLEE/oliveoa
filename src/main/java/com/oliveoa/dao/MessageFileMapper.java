package com.oliveoa.dao;

import com.oliveoa.pojo.MessageFile;

public interface MessageFileMapper {
    int deleteByPrimaryKey(String mfid);

    int insert(MessageFile record);

    int insertSelective(MessageFile record);

    MessageFile selectByPrimaryKey(String mfid);

    int updateByPrimaryKeySelective(MessageFile record);

    int updateByPrimaryKey(MessageFile record);
}