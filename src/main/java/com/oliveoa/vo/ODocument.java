package com.oliveoa.vo;

import com.oliveoa.pojo.OfficialDocument;
import com.oliveoa.pojo.OfficialDocumentCirculread;
import com.oliveoa.pojo.OfficialDocumentIssued;

import java.util.List;

/**
 * Created by Lee on 2018/9/6.
 */
public class ODocument {
    private OfficialDocument officialDocument;
    private List<OfficialDocumentIssued> officialDocumentIssueds;
    private List<OfficialDocumentCirculread> officialDocumentCirculreads;

    public ODocument() {
    }

    public ODocument(OfficialDocument officialDocument, List<OfficialDocumentIssued> officialDocumentIssueds, List<OfficialDocumentCirculread> officialDocumentCirculreads) {
        this.officialDocument = officialDocument;
        this.officialDocumentIssueds = officialDocumentIssueds;
        this.officialDocumentCirculreads = officialDocumentCirculreads;
    }

    public OfficialDocument getOfficialDocument() {
        return officialDocument;
    }

    public void setOfficialDocument(OfficialDocument officialDocument) {
        this.officialDocument = officialDocument;
    }

    public List<OfficialDocumentIssued> getOfficialDocumentIssueds() {
        return officialDocumentIssueds;
    }

    public void setOfficialDocumentIssueds(List<OfficialDocumentIssued> officialDocumentIssueds) {
        this.officialDocumentIssueds = officialDocumentIssueds;
    }

    public List<OfficialDocumentCirculread> getOfficialDocumentCirculreads() {
        return officialDocumentCirculreads;
    }

    public void setOfficialDocumentCirculreads(List<OfficialDocumentCirculread> officialDocumentCirculreads) {
        this.officialDocumentCirculreads = officialDocumentCirculreads;
    }

    @Override
    public String toString() {
        return "ODocument{" +
                "officialDocument=" + officialDocument +
                ", officialDocumentIssueds=" + officialDocumentIssueds +
                ", officialDocumentCirculreads=" + officialDocumentCirculreads +
                '}';
    }
}
