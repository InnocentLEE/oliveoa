package com.oliveoa.dao;

import com.oliveoa.pojo.File;

public interface FileMapper {
    int deleteByPrimaryKey(String fid);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String fid);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}