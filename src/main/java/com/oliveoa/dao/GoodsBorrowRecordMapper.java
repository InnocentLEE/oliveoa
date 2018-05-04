package com.oliveoa.dao;

import com.oliveoa.pojo.GoodsBorrowRecord;

public interface GoodsBorrowRecordMapper {
    int deleteByPrimaryKey(String rid);

    int insert(GoodsBorrowRecord record);

    int insertSelective(GoodsBorrowRecord record);

    GoodsBorrowRecord selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(GoodsBorrowRecord record);

    int updateByPrimaryKey(GoodsBorrowRecord record);
}