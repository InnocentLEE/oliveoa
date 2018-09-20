package com.oliveoa.dao;

import com.oliveoa.pojo.Employees;

import java.util.List;

public interface EmployeesMapper {
    int deleteByPrimaryKey(String eid);

    int insert(Employees record);

    int insertSelective(Employees record);

    Employees selectByPrimaryKey(String eid);

    Employees selectByID(String id);

    List<Employees> selectByDcid(String dcid);

    List<Employees> selectByPcid(String pcid);

    int updateByPrimaryKeySelective(Employees record);

    int updateByPrimaryKey(Employees record);

    int selectCountById(String id);

    int selectCountByPcid(String pcid);

    int selectCountByDcid(String dcid);

    String selectDcidByEid(String eid);
}