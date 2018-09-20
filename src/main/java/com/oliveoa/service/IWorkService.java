package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.IssueWork;
import com.oliveoa.pojo.IssueWorkMember;
import com.oliveoa.pojo.SubmitWork;

import java.util.ArrayList;

/**
 * Created by Lee on 2018/6/4.
 */
public interface IWorkService {
    ServerResponse submit_work(SubmitWork submitWork);

    ServerResponse get_submit_work(String seid, int orderBy);

    ServerResponse get_work_unapproved(String aeid, int orderBy);

    ServerResponse get_work_approved(String eid);

    ServerResponse get_approved_work(String aeid, int orderBy);

    ServerResponse get_work_detail(String swid);

    ServerResponse approved_work(SubmitWork submitWork);

    ServerResponse issue_work(IssueWork issueWork,ArrayList<IssueWorkMember> issueWorkMemberList);

    ServerResponse get_work_IIssue(String eid);

}
