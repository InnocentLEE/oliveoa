package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.Employees;
import com.oliveoa.pojo.Empwd;

/**
 * Created by Lee on 2018/5/28.
 */
public interface IEmployeesService {

    ServerResponse add_employee(Employees employees);
    ServerResponse check_id(String id);
    ServerResponse get_employees_by_department(String dcid);
    ServerResponse get_employees_by_position(String pcid);
    ServerResponse update_employee(Employees employees);
    ServerResponse delete_employee(String eid);
    ServerResponse login(Empwd empwd);
    ServerResponse get_info(String eid);
    ServerResponse update_info(Employees employees);
    ServerResponse update_password(Empwd empwd);
    ServerResponse get_contact();
}
