package com.oliveoa.service.impl;

import com.github.pagehelper.MSUtils;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.FileMapper;
import com.oliveoa.dao.OfficialDocumentIssuedMapper;
import com.oliveoa.dao.OfficialDocumentMapper;
import com.oliveoa.pojo.File;
import com.oliveoa.pojo.OfficialDocument;
import com.oliveoa.pojo.OfficialDocumentIssued;
import com.oliveoa.service.IDocumentFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee on 2018/9/5.
 */
@Service("iDocumentFlowService")
public class DocumentFlowServiceImpl implements IDocumentFlowService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private OfficialDocumentMapper officialDocumentMapper;
    @Autowired
    private OfficialDocumentIssuedMapper officialDocumentIssuedMapper;

    @Override
    public ServerResponse draft(File file, OfficialDocument officialDocument){
        boolean result1 = fileMapper.insertSelective(file)>0;
        boolean result2 = officialDocumentMapper.insertSelective(officialDocument)>0;
        if(result1 && result2)
            return ServerResponse.createBySuccessMessage("拟稿发送成功");
        else
            return ServerResponse.createByErrorMessage("拟稿发送失败");
    }

    @Override
    public File getFile(String fid){
        return fileMapper.selectByPrimaryKey(fid);
    }

    @Override
    public ServerResponse getDocumentIdraft(String eid){
        List<OfficialDocument> officialDocuments = officialDocumentMapper.selectByDraftEid(eid);
        return ServerResponse.createBySuccess(officialDocuments);
    }

    @Override
    public ServerResponse getDocumentNeedNuclear(String eid){
        List<OfficialDocument> officialDocuments = officialDocumentMapper.selectByNuclearEid(eid);
        return ServerResponse.createBySuccess(officialDocuments);
    }

    @Override
    public ServerResponse getDocumentDoneNuclear(String eid){
        List<OfficialDocument> officialDocuments = officialDocumentMapper.selectByApprovedNuclearEid(eid);
        return ServerResponse.createBySuccess(officialDocuments);
    }

    @Override
    public ServerResponse nuclear(OfficialDocument officialDocument){
        officialDocumentMapper.updateByNuclear(officialDocument);
        return ServerResponse.createBySuccessMessage("核稿成功");
    }

    @Override
    public ServerResponse getDocumentNeedIssue(String eid){
        List<OfficialDocument> officialDocuments = officialDocumentMapper.selectByIssueEid(eid);
        return ServerResponse.createBySuccess(officialDocuments);
    }

    @Override
    public ServerResponse getDocumentDoneIssue(String eid){
        List<OfficialDocument> officialDocuments = officialDocumentMapper.selectApprovedByIssueEid(eid);
        return ServerResponse.createBySuccess(officialDocuments);
    }

    @Override
    public ServerResponse issue(OfficialDocument officialDocument, List<OfficialDocumentIssued> officialDocumentIssueds){
        officialDocumentMapper.updateByIssue(officialDocument);
        if(officialDocument.getIssuedIsapproved()==1){
            int size = officialDocumentIssueds.size();
            for(int i=0;i<size;i++){
                officialDocumentIssuedMapper.insertSelective(officialDocumentIssueds.get(i));
            }
        }
        return ServerResponse.createBySuccessMessage("核稿成功");
    }
}
