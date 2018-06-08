package com.oliveoa.vo;

import com.oliveoa.pojo.Employees;
import com.oliveoa.pojo.Position;

/**
 * Created by Lee on 2018/6/2.
 */
public class EmpContact {
    private Employees employees;
    private Position position;

    public EmpContact() {
    }

    public EmpContact(Employees employees, Position position) {
        this.employees = employees;
        this.position = position;
    }

    public Employees getEmployees() {
        return employees;
    }

    public Position getPosition() {
        return position;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "EmpContact{" +
                "employees=" + employees +
                ", position=" + position +
                '}';
    }
}
