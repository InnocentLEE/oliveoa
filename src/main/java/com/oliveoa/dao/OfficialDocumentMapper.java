package com.oliveoa.dao;

import com.oliveoa.pojo.OfficialDocument;

import java.util.List;

public interface OfficialDocumentMapper {
    int deleteByPrimaryKey(String odid);

    int insert(OfficialDocument record);

    int insertSelective(OfficialDocument record);

    OfficialDocument selectByPrimaryKey(String odid);

    int updateByPrimaryKeySelective(OfficialDocument record);

    int updateByPrimaryKey(OfficialDocument record);

    List<OfficialDocument> selectByDraftEid(String eid);

    List<OfficialDocument> selectByNuclearEid(String eid);

    List<OfficialDocument> selectByApprovedNuclearEid(String eid);

    int updateByNuclear(OfficialDocument officialDocument);

    List<OfficialDocument> selectByIssueEid(String eid);

    List<OfficialDocument> selectApprovedByIssueEid(String eid);

    int updateByIssue(OfficialDocument officialDocument);
}