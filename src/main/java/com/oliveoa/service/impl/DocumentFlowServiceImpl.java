package com.oliveoa.service.impl;

import com.github.pagehelper.MSUtils;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.*;
import com.oliveoa.pojo.*;
import com.oliveoa.service.IDocumentFlowService;
import com.oliveoa.util.CommonUtils;
import com.oliveoa.vo.ODocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private OfficialDocumentCirculreadMapper officialDocumentCirculreadMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public ServerResponse draft(File file, OfficialDocument officialDocument){
        boolean result1 = fileMapper.insertSelective(file)>0;
        boolean result2 = officialDocumentMapper.insertSelective(officialDocument)>0;
        if(result1 && result2){
            Message message = new Message();
            message.setMid(CommonUtils.uuid());
            message.setSeid("system_message");
            message.setEid(officialDocument.getNuclearDraftEid());
            message.setMsg("有一条公文正在等待您核稿，请尽快处理。");
            messageMapper.insertSelective(message);
            return ServerResponse.createBySuccessMessage("拟稿发送成功");
        }
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
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(officialDocumentMapper.selectByPrimaryKey(officialDocument.getOdid()).getNuclearDraftEid());
        message.setMsg("您有一条公文已经更新核稿状态，请注意查看。");
        messageMapper.insertSelective(message);
        if(officialDocument.getNuclearDraftIsapproved()==1){
            Message message1 = new Message();
            message1.setMid(CommonUtils.uuid());
            message1.setSeid("system_message");
            message1.setEid(officialDocument.getIssuedEid());
            message1.setMsg("有一条公文已经通过核稿正等您签发，请尽快处理。");
            messageMapper.insertSelective(message1);
        }
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
                Message message = new Message();
                message.setMid(CommonUtils.uuid());
                message.setSeid("system_message");
                String dcid = officialDocumentIssueds.get(i).getDcid();
                message.setEid(employeesMapper.selectDocumentBossByDcid(dcid));
                message.setMsg("有一条公文正等待您签收，请尽快处理。");
                messageMapper.insertSelective(message);
            }
        }
        return ServerResponse.createBySuccessMessage("签发成功");
    }

    @Override
    public ServerResponse getDocumentNeedReceive(String eid){
        String ppid = positionMapper.isPpidIsNull(eid);
        if(ppid!=null)
            return ServerResponse.createBySuccessMessage("没有待你签收的公文");
        List<OfficialDocument> officialDocumentList = officialDocumentMapper.selectNeedReceive(eid);
        return ServerResponse.createBySuccess(officialDocumentList);
    }

    @Override
    public ServerResponse getDocumentReceived(String eid){
        String ppid = positionMapper.isPpidIsNull(eid);
        if(ppid!=null)
            return ServerResponse.createBySuccessMessage("没有已经签收的公文");
        List<OfficialDocument> officialDocumentList = officialDocumentMapper.selectReceived(eid);
        return ServerResponse.createBySuccess(officialDocumentList);
    }

    @Override
    public ServerResponse receive(String eid,OfficialDocumentIssued officialDocumentIssued,List<OfficialDocumentCirculread> officialDocumentCirculreads){
        officialDocumentIssued.setDcid(employeesMapper.selectDcidByEid(eid));
        officialDocumentIssuedMapper.updateByOiidAndDcid(officialDocumentIssued);
        int size = officialDocumentCirculreads.size();
        for (int i = 0; i < size; i++) {
            officialDocumentCirculreadMapper.insertSelective(officialDocumentCirculreads.get(i));
            Message message = new Message();
            message.setMid(CommonUtils.uuid());
            message.setSeid("system_message");
            message.setEid(officialDocumentCirculreads.get(i).getEid());
            message.setMsg("有一条公文正等待您办理，请尽快处理。");
            messageMapper.insertSelective(message);
        }
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(officialDocumentMapper.selectByPrimaryKey(officialDocumentIssued.getOiid()).getIssuedEid());
        message.setMsg("有一条公文已经被签收，请注意查看。");
        messageMapper.insertSelective(message);
        return ServerResponse.createBySuccessMessage("签收成功");
    }

    @Override
    public ServerResponse getDocumentNeedRead(String eid){
        List<OfficialDocument> officialDocumentList = officialDocumentMapper.selectNeedRead(eid);
        return ServerResponse.createBySuccess(officialDocumentList);
    }

    @Override
    public ServerResponse getDocumentHaveRead(String eid){
        List<OfficialDocument> officialDocumentList = officialDocumentMapper.selectHaveRead(eid);
        return ServerResponse.createBySuccess(officialDocumentList);
    }

    @Override
    public ServerResponse read(OfficialDocumentCirculread officialDocumentCirculread){
        officialDocumentCirculreadMapper.updateByOiidAndEid(officialDocumentCirculread);
        Message message = new Message();
        message.setMid(CommonUtils.uuid());
        message.setSeid("system_message");
        message.setEid(officialDocumentMapper.selectByPrimaryKey(officialDocumentCirculread.getOiid()).getIssuedEid());
        message.setMsg("有一条公文的办理状态有所更新，请注意查看。");
        messageMapper.insertSelective(message);
        return ServerResponse.createBySuccessMessage("报告成功");
    }

    @Override
    public ServerResponse getDocumentDetails(String odid){
        OfficialDocument officialDocument = officialDocumentMapper.selectByPrimaryKey(odid);
        List<OfficialDocumentIssued> officialDocumentIssueds = officialDocumentIssuedMapper.selectByOiid(odid);
        List<OfficialDocumentCirculread> officialDocumentCirculreads = officialDocumentCirculreadMapper.selectByOiid(odid);
        ODocument oDocument = new ODocument();
        oDocument.setOfficialDocument(officialDocument);
        oDocument.setOfficialDocumentCirculreads(officialDocumentCirculreads);
        oDocument.setOfficialDocumentIssueds(officialDocumentIssueds);
        return ServerResponse.createBySuccess(oDocument);
    }

    @Override
    public ServerResponse getDocumentList(){
        List<String> odids = officialDocumentMapper.selectAllOdid();
        int size = odids.size();
        List<ODocument> oDocumentList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            OfficialDocument officialDocument = officialDocumentMapper.selectByPrimaryKey(odids.get(i));
            List<OfficialDocumentIssued> officialDocumentIssueds = officialDocumentIssuedMapper.selectByOiid(odids.get(i));
            List<OfficialDocumentCirculread> officialDocumentCirculreads = officialDocumentCirculreadMapper.selectByOiid(odids.get(i));
            ODocument oDocument = new ODocument();
            oDocument.setOfficialDocument(officialDocument);
            oDocument.setOfficialDocumentCirculreads(officialDocumentCirculreads);
            oDocument.setOfficialDocumentIssueds(officialDocumentIssueds);
            oDocumentList.add(oDocument);
        }
        return ServerResponse.createBySuccess(oDocumentList);
    }
}
