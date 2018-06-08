package com.oliveoa.dao;

import com.oliveoa.pojo.SubmitWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubmitWorkMapper {
    int deleteByPrimaryKey(String swid);

    int insert(SubmitWork record);

    int insertSelective(SubmitWork record);

    SubmitWork selectByPrimaryKey(String swid);

    int updateByPrimaryKeySelective(SubmitWork record);

    int updateByPrimaryKey(SubmitWork record);

    List<SubmitWork> selectUnapprovedByAeid(@Param("aeid") String aeid, @Param("orderBy") Integer orderBy);

    List<SubmitWork> selectByAeid(@Param("aeid") String aeid, @Param("orderBy") Integer orderBy);

    List<SubmitWork> selectBySeid(@Param("seid") String seid, @Param("orderBy") Integer orderBy);
}