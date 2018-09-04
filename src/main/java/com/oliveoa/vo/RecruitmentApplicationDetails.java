package com.oliveoa.vo;

import com.oliveoa.pojo.RecruitmentApplication;
import com.oliveoa.pojo.RecruitmentApplicationApprovedOpinion;
import com.oliveoa.pojo.RecruitmentApplicationItem;

import java.util.List;

/**
 * Created by Lee on 2018/9/3.
 */
public class RecruitmentApplicationDetails {
    private RecruitmentApplication recruitmentApplication;
    private RecruitmentApplicationItem recruitmentApplicationItem;
    private List<RecruitmentApplicationApprovedOpinion> recruitmentApplicationApprovedOpinions;

    public RecruitmentApplicationDetails() {
    }

    public RecruitmentApplicationDetails(RecruitmentApplicationItem recruitmentApplicationItem, RecruitmentApplication recruitmentApplication, List<RecruitmentApplicationApprovedOpinion> recruitmentApplicationApprovedOpinions) {
        this.recruitmentApplicationItem = recruitmentApplicationItem;
        this.recruitmentApplication = recruitmentApplication;
        this.recruitmentApplicationApprovedOpinions = recruitmentApplicationApprovedOpinions;
    }

    public RecruitmentApplication getRecruitmentApplication() {
        return recruitmentApplication;
    }

    public void setRecruitmentApplication(RecruitmentApplication recruitmentApplication) {
        this.recruitmentApplication = recruitmentApplication;
    }

    public RecruitmentApplicationItem getRecruitmentApplicationItem() {
        return recruitmentApplicationItem;
    }

    public void setRecruitmentApplicationItem(RecruitmentApplicationItem recruitmentApplicationItem) {
        this.recruitmentApplicationItem = recruitmentApplicationItem;
    }

    public List<RecruitmentApplicationApprovedOpinion> getRecruitmentApplicationApprovedOpinions() {
        return recruitmentApplicationApprovedOpinions;
    }

    public void setRecruitmentApplicationApprovedOpinions(List<RecruitmentApplicationApprovedOpinion> recruitmentApplicationApprovedOpinions) {
        this.recruitmentApplicationApprovedOpinions = recruitmentApplicationApprovedOpinions;
    }

    @Override
    public String toString() {
        return "RecruitmentApplicationDetails{" +
                "recruitmentApplication=" + recruitmentApplication +
                ", recruitmentApplicationItem=" + recruitmentApplicationItem +
                ", recruitmentApplicationApprovedOpinions=" + recruitmentApplicationApprovedOpinions +
                '}';
    }
}
