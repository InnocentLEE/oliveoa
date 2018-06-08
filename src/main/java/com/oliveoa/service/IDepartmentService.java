package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.Department;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Lee on 2018/5/18.
 */
public interface IDepartmentService {
    ServerResponse<String> add_department(Department department);
    ServerResponse check_departmentID(String id);
    ServerResponse<Department> get_departmentByDcid(String dcid);
    ServerResponse<List<Department>> get_department();
    ServerResponse<List<Department>> get_children_parallel_department(String dpid);
    ServerResponse update_department(Department department);
    ServerResponse delete_department(String dcid);

}
