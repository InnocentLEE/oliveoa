package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.File;
import com.oliveoa.pojo.OfficialDocument;
import com.oliveoa.pojo.OfficialDocumentCirculread;
import com.oliveoa.pojo.OfficialDocumentIssued;

import java.util.List;

/**
 * Created by Lee on 2018/9/5.
 */
public interface IDocumentFlowService {
    ServerResponse draft(File file, OfficialDocument officialDocument);
    File getFile(String fid);
    ServerResponse getDocumentIdraft(String eid);
    ServerResponse getDocumentNeedNuclear(String eid);
    ServerResponse getDocumentDoneNuclear(String eid);
    ServerResponse nuclear(OfficialDocument officialDocument);
    ServerResponse getDocumentNeedIssue(String eid);
    ServerResponse getDocumentDoneIssue(String eid);
    ServerResponse issue(OfficialDocument officialDocument, List<OfficialDocumentIssued> officialDocumentIssueds);
    ServerResponse getDocumentNeedReceive(String eid);
    ServerResponse getDocumentReceived(String eid);
    ServerResponse receive(String eid,OfficialDocumentIssued officialDocumentIssued,List<OfficialDocumentCirculread> officialDocumentCirculreads);
    ServerResponse getDocumentNeedRead(String eid);
    ServerResponse getDocumentHaveRead(String eid);
    ServerResponse read(OfficialDocumentCirculread officialDocumentCirculread);
    ServerResponse getDocumentDetails(String odid);
    ServerResponse getDocumentList();
}
