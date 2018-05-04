package com.oliveoa.dao;

import com.oliveoa.pojo.OfficialDocument;

public interface OfficialDocumentMapper {
    int deleteByPrimaryKey(String odid);

    int insert(OfficialDocument record);

    int insertSelective(OfficialDocument record);

    OfficialDocument selectByPrimaryKey(String odid);

    int updateByPrimaryKeySelective(OfficialDocument record);

    int updateByPrimaryKey(OfficialDocument record);
}