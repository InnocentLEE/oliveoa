package com.oliveoa.controller.employees;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.*;
import com.oliveoa.service.IDocumentFlowService;
import com.oliveoa.util.CommonUtils;
import com.oliveoa.vo.MemberBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Lee on 2018/9/5.
 */
@Controller
@RequestMapping("/documentflow/")
public class DocumentFlowController {

    @Autowired
    private IDocumentFlowService iDocumentFlowService;

    @RequestMapping(value="draft.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse draft(MultipartFile file, HttpServletRequest request, HttpSession session,String title,String content,String nuclearDraftEid,String issuedEid) throws IOException {
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        String odid = CommonUtils.uuid();
        String fid = odid;
        String path = request.getSession().getServletContext().getRealPath("/upload");
        String fileName = file.getOriginalFilename();
        String ZfileName = fid + fileName;
        File dir = new File(path,ZfileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        file.transferTo(dir);
        com.oliveoa.pojo.File file1 = new com.oliveoa.pojo.File();
        file1.setFid(fid);
        file1.setFname(fileName);
        file1.setFsrc("/"+ZfileName);
        OfficialDocument officialDocument = new OfficialDocument();
        officialDocument.setOdid(odid);
        officialDocument.setDraftEid(employees.getEid());
        officialDocument.setContent(content);
        officialDocument.setFid(fid);
        officialDocument.setTitle(title);
        officialDocument.setNuclearDraftEid(nuclearDraftEid);
        officialDocument.setIssuedEid(issuedEid);
        officialDocument.setNuclearDraftIsapproved(0);
        officialDocument.setIssuedIsapproved(0);
        return iDocumentFlowService.draft(file1,officialDocument);
    }

    @RequestMapping(value="download.do",method= RequestMethod.GET)
    public void download(HttpServletRequest request,HttpServletResponse response, String odid) throws Exception{
        com.oliveoa.pojo.File file = iDocumentFlowService.getFile(odid);
        //需要下载的文件
        String fileName = request.getSession().getServletContext().getRealPath("/upload")+file.getFsrc();
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //转码，免得文件名中文乱码
        String filename = URLEncoder.encode(file.getFname(),"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }


    @RequestMapping(value="get_document_Idraft.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentIdraft(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentIdraft(employees.getEid());
    }

    @RequestMapping(value="get_document_need_nuclear.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentNeedNuclear(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentNeedNuclear(employees.getEid());
    }

    @RequestMapping(value="get_document_done_nuclear.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentDoneNuclear(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentDoneNuclear(employees.getEid());
    }

    @RequestMapping(value="nuclear.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse nuclear(HttpSession session,String isApproved,String oponion,String odid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        OfficialDocument officialDocument = new OfficialDocument();
        officialDocument.setOdid(odid);
        officialDocument.setNuclearDraftEid(employees.getEid());
        officialDocument.setNuclearDraftIsapproved(Integer.parseInt(isApproved));
        officialDocument.setNuclearDraftOpinion(oponion);
        return iDocumentFlowService.nuclear(officialDocument);
    }

    @RequestMapping(value="get_document_need_Issue.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentNeedIssue(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentNeedIssue(employees.getEid());
    }

    @RequestMapping(value="get_document_done_Issue.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentDoneIssue(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentDoneIssue(employees.getEid());
    }

    @RequestMapping(value="issue.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse issue(HttpSession session,String isApproved,String oponion,String odid,String departments){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        OfficialDocument officialDocument = new OfficialDocument();
        officialDocument.setOdid(odid);
        officialDocument.setIssuedEid(employees.getEid());
        officialDocument.setIssuedIsapproved(Integer.parseInt(isApproved));
        officialDocument.setIssuedOpinion(oponion);
        ArrayList<OfficialDocumentIssued> officialDocumentIssueds = new ArrayList<>();
        if(!departments.equals("")){
            JsonParser parser = new JsonParser();
            //将JSON的String 转成一个JsonArray对象
            JsonArray jsonArray = parser.parse(departments).getAsJsonArray();
            Gson gson = new Gson();
            //加强for循环遍历JsonArray
            for (JsonElement member : jsonArray) {
                //使用GSON，直接转成Bean对象]
                MemberBean memberBean = gson.fromJson(member, MemberBean.class);
                OfficialDocumentIssued officialDocumentIssued = new OfficialDocumentIssued();
                officialDocumentIssued.setOdiid(CommonUtils.uuid());
                officialDocumentIssued.setOiid(odid);
                officialDocumentIssued.setDcid(memberBean.getId());
                officialDocumentIssued.setIsreceive(0);
                officialDocumentIssueds.add(officialDocumentIssued);
            }
        }
        return iDocumentFlowService.issue(officialDocument,officialDocumentIssueds);
    }

    @RequestMapping(value="get_document_need_receive.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentNeedReceive(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentNeedReceive(employees.getEid());
    }

    @RequestMapping(value="get_document_received.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentReceived(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentReceived(employees.getEid());
    }

    @RequestMapping(value="receive.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse receive(HttpSession session,String isReceive,String odid,String members){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        OfficialDocumentIssued officialDocumentIssued = new OfficialDocumentIssued();
        officialDocumentIssued.setOiid(odid);
        officialDocumentIssued.setIsreceive(Integer.parseInt(isReceive));
        ArrayList<OfficialDocumentCirculread> officialDocumentCirculreads = new ArrayList<>();
        if(!members.equals("")){
            JsonParser parser = new JsonParser();
            //将JSON的String 转成一个JsonArray对象
            JsonArray jsonArray = parser.parse(members).getAsJsonArray();
            Gson gson = new Gson();
            //加强for循环遍历JsonArray
            for (JsonElement member : jsonArray) {
                //使用GSON，直接转成Bean对象]
                MemberBean memberBean = gson.fromJson(member, MemberBean.class);
                OfficialDocumentCirculread officialDocumentCirculread = new OfficialDocumentCirculread();
                officialDocumentCirculread.setOdcid(CommonUtils.uuid());
                officialDocumentCirculread.setOiid(odid);
                officialDocumentCirculread.setEid(memberBean.getId());
                officialDocumentCirculread.setIsread(0);
                officialDocumentCirculreads.add(officialDocumentCirculread);
            }
        }

        return iDocumentFlowService.receive(employees.getEid(),officialDocumentIssued,officialDocumentCirculreads);
    }


    @RequestMapping(value="get_document_need_read.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentNeedRead(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentNeedRead(employees.getEid());
    }

    @RequestMapping(value="get_document_have_read.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentHaveRead(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentHaveRead(employees.getEid());
    }

    @RequestMapping(value="read.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse read(HttpSession session,String odid,String report){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        OfficialDocumentCirculread officialDocumentCirculread = new OfficialDocumentCirculread();
        officialDocumentCirculread.setOiid(odid);
        officialDocumentCirculread.setEid(employees.getEid());
        officialDocumentCirculread.setIsread(1);
        officialDocumentCirculread.setReport(report);
        return iDocumentFlowService.read(officialDocumentCirculread);
    }

    @RequestMapping(value="get_document_details.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentDetails(HttpSession session,String odid){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentDetails(odid);
    }

    @RequestMapping(value="get_document_list.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDocumentList(HttpSession session){
        Employees employees = (Employees) session.getAttribute(Const.CURRENT_USER);
        if (employees == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        return iDocumentFlowService.getDocumentList();
    }














}
