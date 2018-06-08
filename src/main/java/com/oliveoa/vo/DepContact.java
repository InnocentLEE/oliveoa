package com.oliveoa.vo;

import com.oliveoa.pojo.Department;

import java.util.List;

/**
 * Created by Lee on 2018/6/2.
 */
public class DepContact {
    private Department department;
    private List<EmpContact> empContactList;

    public DepContact() {
    }

    public DepContact(Department department, List<EmpContact> empContactList) {
        this.department = department;
        this.empContactList = empContactList;
    }

    public Department getDepartment() {
        return department;
    }

    public List<EmpContact> getEmpContactList() {
        return empContactList;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setEmpContactList(List<EmpContact> empContactList) {
        this.empContactList = empContactList;
    }

    @Override
    public String toString() {
        return "DepContact{" +
                "department=" + department +
                ", empContactList=" + empContactList +
                '}';
    }
}
