package com.oliveoa.vo;

import com.oliveoa.pojo.JobTransferApplication;
import com.oliveoa.pojo.JobTransferApplicationApprovedOpinion;

import java.util.List;

/**
 * Created by Lee on 2018/8/28.
 */
public class JobTransferApplicationDetails {
    private JobTransferApplication jobTransferApplication;
    private List<JobTransferApplicationApprovedOpinion> jobTransferApplicationApprovedOpinionList;

    public JobTransferApplicationDetails() {
    }

    public List<JobTransferApplicationApprovedOpinion> getJobTransferApplicationApprovedOpinionList() {
        return jobTransferApplicationApprovedOpinionList;
    }

    public void setJobTransferApplicationApprovedOpinionList(List<JobTransferApplicationApprovedOpinion> jobTransferApplicationApprovedOpinionList) {
        this.jobTransferApplicationApprovedOpinionList = jobTransferApplicationApprovedOpinionList;
    }

    public JobTransferApplication getJobTransferApplication() {
        return jobTransferApplication;
    }

    public void setJobTransferApplication(JobTransferApplication jobTransferApplication) {
        this.jobTransferApplication = jobTransferApplication;
    }

    public JobTransferApplicationDetails(JobTransferApplication jobTransferApplication, List<JobTransferApplicationApprovedOpinion> jobTransferApplicationApprovedOpinionList) {

        this.jobTransferApplication = jobTransferApplication;
        this.jobTransferApplicationApprovedOpinionList = jobTransferApplicationApprovedOpinionList;
    }

    @Override
    public String toString() {
        return "JobTransferApplicationDetails{" +
                "jobTransferApplication=" + jobTransferApplication +
                ", jobTransferApplicationApprovedOpinionList=" + jobTransferApplicationApprovedOpinionList +
                '}';
    }
}
