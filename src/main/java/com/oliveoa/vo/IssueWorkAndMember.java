package com.oliveoa.vo;

import com.oliveoa.pojo.Employees;
import com.oliveoa.pojo.IssueWork;
import com.oliveoa.pojo.IssueWorkMember;

import java.util.List;

/**
 * Created by Lee on 2018/6/7.
 */
public class IssueWorkAndMember {
    private IssueWork issueWork;
    private List<Employees> employeesList;

    public IssueWorkAndMember() {
    }

    public IssueWorkAndMember(IssueWork issueWork, List<Employees> employeesList) {
        this.issueWork = issueWork;
        this.employeesList = employeesList;
    }

    public IssueWork getIssueWork() {
        return issueWork;
    }

    public void setIssueWork(IssueWork issueWork) {
        this.issueWork = issueWork;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    @Override
    public String toString() {
        return "IssueWorkAndMember{" +
                "issueWork=" + issueWork +
                ", employeesList=" + employeesList +
                '}';
    }
}
