package com.oliveoa.dao;

import com.oliveoa.pojo.JobTransferApplication;
import com.oliveoa.pojo.JobTransferApplicationApprovedOpinion;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface JobTransferApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String jtaaocid);

    int insert(JobTransferApplicationApprovedOpinion record);

    int insertSelective(JobTransferApplicationApprovedOpinion record);

    JobTransferApplicationApprovedOpinion selectByPrimaryKey(String jtaaocid);

    int updateByPrimaryKeySelective(JobTransferApplicationApprovedOpinion record);

    int updateByPrimaryKey(JobTransferApplicationApprovedOpinion record);

    List<JobTransferApplicationApprovedOpinion> selectByJtaid(@Param("jtaid") String jtaid);

    int updateByJtaidAndEid(JobTransferApplicationApprovedOpinion record);

    String selectJtaaopidByBtaidAndEid(JobTransferApplicationApprovedOpinion record);

    int updateIsApprovedToZeroByJtaaocid(@Param("jtaaocid") String jtaaocid);
}