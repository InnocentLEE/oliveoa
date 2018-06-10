package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.DepartmentMapper;
import com.oliveoa.dao.EmployeesMapper;
import com.oliveoa.dao.EmpwdMapper;
import com.oliveoa.dao.PositionMapper;
import com.oliveoa.pojo.Department;
import com.oliveoa.pojo.Employees;
import com.oliveoa.pojo.Empwd;
import com.oliveoa.pojo.Position;
import com.oliveoa.service.IEmployeesService;
import com.oliveoa.vo.DepContact;
import com.oliveoa.vo.EmpContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2018/5/28.
 */
@Service("iEmployeesService")
public class EmployeesServiceImpl implements IEmployeesService {

    @Autowired
    private EmployeesMapper employeesMapper;

    @Autowired
    private EmpwdMapper empwdMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public ServerResponse add_employee(Employees employees) {
        int result = employeesMapper.insert(employees);
        Empwd empwd = new Empwd(employees.getId(), employees.getId());
        int result1 = empwdMapper.insert(empwd);
        if (result > 0 && result1 > 0)
            return ServerResponse.createBySuccessMessage("录入员工成功");
        else
            return ServerResponse.createByErrorMessage("录入员工失败");
    }

    @Override
    public ServerResponse check_id(String id) {
        int result = employeesMapper.selectCountById(id);
        if (result > 0)
            return ServerResponse.createByErrorMessage("ID已被使用");
        else
            return ServerResponse.createBySuccessMessage("ID未被使用");
    }

    @Override
    public ServerResponse get_employees_by_department(String dcid) {
        List<Employees> employeesList = employeesMapper.selectByDcid(dcid);
        return ServerResponse.createBySuccess("查找员工成功", employeesList);
    }

    @Override
    public ServerResponse get_employees_by_position(String pcid) {
        List<Employees> employeesList = employeesMapper.selectByPcid(pcid);
        return ServerResponse.createBySuccess("查找员工成功", employeesList);
    }

    @Override
    public ServerResponse update_employee(Employees employees) {
        int result = employeesMapper.updateByPrimaryKeySelective(employees);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("更新员工信息成功");
        else
            return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse delete_employee(String eid) {
        String id = employeesMapper.selectByPrimaryKey(eid).getId();
        empwdMapper.deleteByPrimaryKey(id);
        int result = employeesMapper.deleteByPrimaryKey(eid);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("删除成功");
        else
            return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse login(Empwd empwd) {
        int result = empwdMapper.select(empwd);
        if (result > 0) {
            Employees employees = employeesMapper.selectByID(empwd.getId());
            return ServerResponse.createBySuccess("登录成功", employees);
        } else
            return ServerResponse.createByErrorMessage("用户名或密码错误");
    }

    @Override
    public ServerResponse get_info(String eid) {
        Employees employees = employeesMapper.selectByPrimaryKey(eid);
        if (employees != null)
            return ServerResponse.createBySuccess("查询成功", employees);
        else
            return ServerResponse.createByErrorMessage("查询失败");
    }

    @Override
    public ServerResponse update_info(Employees employees) {
        int result = employeesMapper.updateByPrimaryKeySelective(employees);
        if (result > 0) {
            Employees employees1 = (Employees) get_info(employees.getEid()).getData();
            return ServerResponse.createBySuccess("更新成功", employees1);
        } else {
            return ServerResponse.createByErrorMessage("更新个人信息失败");
        }
    }

    @Override
    public ServerResponse update_password(Empwd empwd) {
        int result = empwdMapper.updateByPrimaryKey(empwd);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("修改密码成功");
        else
            return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse get_contact(){
        List<Department> departmentList = departmentMapper.select();
        List<DepContact> depContactList = new ArrayList<DepContact>();
        int departmentNumber = departmentList.size();
        for(int i=0;i<departmentNumber;i++){
            DepContact depContact = new DepContact();
            depContact.setDepartment(departmentList.get(i));
            List<EmpContact> empContactList = new ArrayList<EmpContact>();
            List<Employees> employeesList = employeesMapper.selectByDcid(departmentList.get(i).getDcid());
            int m = employeesList.size();
            for(int j=0;j<m;j++){
                Position position = positionMapper.selectByPrimaryKey(employeesList.get(j).getPcid());
                EmpContact empContact = new EmpContact(employeesList.get(j),position);
                empContactList.add(empContact);
            }
            depContact.setEmpContactList(empContactList);
            depContactList.add(depContact);
        }
        return ServerResponse.createBySuccess("查询成功",depContactList);
    }
}