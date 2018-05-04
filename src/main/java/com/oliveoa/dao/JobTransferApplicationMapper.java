package com.oliveoa.dao;

import com.oliveoa.pojo.JobTransferApplication;

public interface JobTransferApplicationMapper {
    int deleteByPrimaryKey(String jtaid);

    int insert(JobTransferApplication record);

    int insertSelective(JobTransferApplication record);

    JobTransferApplication selectByPrimaryKey(String jtaid);

    int updateByPrimaryKeySelective(JobTransferApplication record);

    int updateByPrimaryKey(JobTransferApplication record);
}