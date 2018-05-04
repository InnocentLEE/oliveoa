package com.oliveoa.dao;

import com.oliveoa.pojo.OfficialDocumentCirculread;

public interface OfficialDocumentCirculreadMapper {
    int deleteByPrimaryKey(String odcid);

    int insert(OfficialDocumentCirculread record);

    int insertSelective(OfficialDocumentCirculread record);

    OfficialDocumentCirculread selectByPrimaryKey(String odcid);

    int updateByPrimaryKeySelective(OfficialDocumentCirculread record);

    int updateByPrimaryKey(OfficialDocumentCirculread record);
}