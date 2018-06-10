package com.oliveoa.dao;

import com.oliveoa.pojo.OvertimeApplicationApprovedOpinion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OvertimeApplicationApprovedOpinionMapper {
    int deleteByPrimaryKey(String oaaocid);

    int insert(OvertimeApplicationApprovedOpinion record);

    int insertSelective(OvertimeApplicationApprovedOpinion record);

    OvertimeApplicationApprovedOpinion selectByPrimaryKey(String oaaocid);

    int updateByPrimaryKeySelective(OvertimeApplicationApprovedOpinion record);

    int updateByPrimaryKey(OvertimeApplicationApprovedOpinion record);

    List<OvertimeApplicationApprovedOpinion> selectNeedApprovedByEid(String eid);

    List<OvertimeApplicationApprovedOpinion> selectByOaid(String oaid);

    int updateByOaidAndEid(OvertimeApplicationApprovedOpinion overtimeApplicationApprovedOpinion);

    String selectOaaopidByOaidAndEid(OvertimeApplicationApprovedOpinion overtimeApplicationApprovedOpinion);

    int updateIsApprovedToZeroByOaaocid(String oaaocid);
}