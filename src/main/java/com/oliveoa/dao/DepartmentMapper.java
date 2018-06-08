package com.oliveoa.dao;

import com.oliveoa.pojo.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String dcid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String dcid);

    List<Department> select();

    List<Department> selectByDpid(String dpid);

    List<Department> selectByDpidIsNULL();

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    int selectCountById(String id);

    int selectCountByDpid(String dpid);
}