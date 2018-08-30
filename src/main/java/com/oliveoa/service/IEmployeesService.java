package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.*;

import java.util.List;

/**
 * Created by Lee on 2018/5/28.
 */
public interface IEmployeesService {

    ServerResponse add_employee(Employees employees);
    ServerResponse check_id(String id);
    ServerResponse get_employees_by_department(String dcid);
    ServerResponse get_employees_by_position(String pcid);
    ServerResponse update_employee(Employees employees);
    ServerResponse delete_employee(String eid);
    ServerResponse login(Empwd empwd);
    ServerResponse get_info(String eid);
    ServerResponse update_info(Employees employees);
    ServerResponse update_password(Empwd empwd);
    ServerResponse get_contact();
    ServerResponse sent_message(Message message);
    ServerResponse get_message_Isent(String seid,int orderBy);
    ServerResponse get_message_sent_to_me(String eid,int orderBy);
    ServerResponse submit_announcement(Announcement announcement, List<AnnouncementApprovedOpinion> announcementApprovedOpinionList);
    ServerResponse get_announcement_need_approved(String eid);
    ServerResponse get_announcement_Isubmit(String eid);
    ServerResponse approved_announcement(AnnouncementApprovedOpinion announcementApprovedOpinion);
    ServerResponse get_announcement_details(String aid);
    ServerResponse get_announcement_publish();
}
