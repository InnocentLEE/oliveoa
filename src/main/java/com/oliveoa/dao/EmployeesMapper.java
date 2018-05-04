package com.oliveoa.dao;

import com.oliveoa.pojo.Employees;

public interface EmployeesMapper {
    int deleteByPrimaryKey(String eid);

    int insert(Employees record);

    int insertSelective(Employees record);

    Employees selectByPrimaryKey(String eid);

    int updateByPrimaryKeySelective(Employees record);

    int updateByPrimaryKey(Employees record);
}