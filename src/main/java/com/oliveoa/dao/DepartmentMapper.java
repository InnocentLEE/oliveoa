package com.oliveoa.dao;

import com.oliveoa.pojo.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String dcid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String dcid);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}