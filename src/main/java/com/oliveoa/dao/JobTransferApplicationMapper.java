package com.oliveoa.dao;

import com.oliveoa.pojo.JobTransferApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobTransferApplicationMapper {
    int deleteByPrimaryKey(String jtaid);

    int insert(JobTransferApplication record);

    int insertSelective(JobTransferApplication record);

    JobTransferApplication selectByPrimaryKey(String jtaid);

    int updateByPrimaryKeySelective(JobTransferApplication record);

    int updateByPrimaryKey(JobTransferApplication record);

    List<JobTransferApplication> selectByApprovedEid(@Param("eid") String eid);

    List<JobTransferApplication> selectByAEid(@Param("aeid") String aeid);
}