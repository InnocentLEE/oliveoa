package com.oliveoa.service.impl;

import com.google.common.collect.Lists;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.DepartmentMapper;
import com.oliveoa.dao.EmployeesMapper;
import com.oliveoa.dao.PositionMapper;
import com.oliveoa.pojo.Department;
import com.oliveoa.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Lee on 2018/5/18.
 */
@Service("iDepartmentService")
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    public ServerResponse add_department(Department department) {
        int result = departmentMapper.insert(department);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("添加部门成功");
        } else {
            return ServerResponse.createByErrorMessage("添加部门失败");
        }
    }

    @Override
    public ServerResponse check_departmentID(String id) {
        int result = departmentMapper.selectCountById(id);
        if (result == 0) {
            return ServerResponse.createBySuccessMessage("id未被使用");
        } else {
            return ServerResponse.createByErrorMessage("id已存在");
        }
    }

    @Override
    public ServerResponse<Department> get_departmentByDcid(String dcid) {
        Department department = departmentMapper.selectByPrimaryKey(dcid);
        if (department != null) {
            return ServerResponse.createBySuccess(department);
        }
        return ServerResponse.createByErrorMessage("查找不到该部门");
    }

    @Override
    public ServerResponse<List<Department>> get_department() {
        List<Department> departmentList = departmentMapper.select();
        if (departmentList.size() > 0)
            return ServerResponse.createBySuccess(departmentList);
        return ServerResponse.createByErrorMessage("没有部门");
    }

    @Override
    public ServerResponse<List<Department>> get_children_parallel_department(String dpid) {
        List<Department> departmentList = null;
        if (dpid != null)
            departmentList = departmentMapper.selectByDpid(dpid);
        else
            departmentList = departmentMapper.selectByDpidIsNULL();
        if (departmentList.size() > 0)
            return ServerResponse.createBySuccess(departmentList);
        return ServerResponse.createByErrorMessage("没有部门");
    }

    @Override
    public ServerResponse update_department(Department department) {
        int result = departmentMapper.updateByPrimaryKey(department);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("修改部门信息成功");
        return ServerResponse.createByErrorMessage("修改部门信息失败");
    }

    @Override
    public ServerResponse delete_department(String dcid) {
        //是否有子级部门
        int children = departmentMapper.selectCountByDpid(dcid);
        if (children > 0)
            return ServerResponse.createByErrorMessage("该部门下有子级部门，无法删除");
        //部门下是否有职务
        int positionNumber = positionMapper.selectCountByDcid(dcid);
        if (positionNumber > 0)
            return ServerResponse.createByErrorMessage("该部门下有职务，无法删除");
        //部门下是否有员工
        int employeeNumber = positionMapper.selectCountByDcid(dcid);
        if (employeeNumber > 0)
            return ServerResponse.createByErrorMessage("该部门下有员工，无法删除");
        //删除部门
        int result = departmentMapper.deleteByPrimaryKey(dcid);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("删除部门成功");
        else
            return ServerResponse.createByErrorMessage("删除部门失败");
    }
}
