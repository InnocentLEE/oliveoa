package com.oliveoa.dao;

import com.oliveoa.pojo.OfficialDocumentCirculread;

import java.util.List;

public interface OfficialDocumentCirculreadMapper {
    int deleteByPrimaryKey(String odcid);

    int insert(OfficialDocumentCirculread record);

    int insertSelective(OfficialDocumentCirculread record);

    OfficialDocumentCirculread selectByPrimaryKey(String odcid);

    int updateByPrimaryKeySelective(OfficialDocumentCirculread record);

    int updateByPrimaryKey(OfficialDocumentCirculread record);

    int updateByOiidAndEid(OfficialDocumentCirculread record);

    List<OfficialDocumentCirculread> selectByOiid(String oiid);
}