package com.oliveoa.dao;

import com.oliveoa.pojo.JobTransferApplicationApprovedOpinion;

public interface JobTransferApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String jtaaocid);

    int insert(JobTransferApplicationApprovedOpinion record);

    int insertSelective(JobTransferApplicationApprovedOpinion record);

    JobTransferApplicationApprovedOpinion selectByPrimaryKey(String jtaaocid);

    int updateByPrimaryKeySelective(JobTransferApplicationApprovedOpinion record);

    int updateByPrimaryKey(JobTransferApplicationApprovedOpinion record);
}