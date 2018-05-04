package com.oliveoa.dao;

import com.oliveoa.pojo.OfficialDocumentIssued;

public interface OfficialDocumentIssuedMapper {
    int deleteByPrimaryKey(String odiid);

    int insert(OfficialDocumentIssued record);

    int insertSelective(OfficialDocumentIssued record);

    OfficialDocumentIssued selectByPrimaryKey(String odiid);

    int updateByPrimaryKeySelective(OfficialDocumentIssued record);

    int updateByPrimaryKey(OfficialDocumentIssued record);
}