package com.oliveoa.vo;

import com.oliveoa.pojo.Announcement;
import com.oliveoa.pojo.AnnouncementApprovedOpinion;

import java.util.List;

/**
 * Created by Lee on 2018/8/25.
 */
public class AnnouncementDetails {
    private Announcement announcement;
    private List<AnnouncementApprovedOpinion> announcementApprovedOpinionList;

    public AnnouncementDetails() {
    }

    public AnnouncementDetails(Announcement announcement, List<AnnouncementApprovedOpinion> announcementApprovedOpinionList) {
        this.announcement = announcement;
        this.announcementApprovedOpinionList = announcementApprovedOpinionList;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public List<AnnouncementApprovedOpinion> getAnnouncementApprovedOpinionList() {
        return announcementApprovedOpinionList;
    }

    public void setAnnouncementApprovedOpinionList(List<AnnouncementApprovedOpinion> announcementApprovedOpinionList) {
        this.announcementApprovedOpinionList = announcementApprovedOpinionList;
    }

    @Override
    public String toString() {
        return "AnnouncementDetails{" +
                "announcement=" + announcement +
                ", announcementApprovedOpinionList=" + announcementApprovedOpinionList +
                '}';
    }
}
