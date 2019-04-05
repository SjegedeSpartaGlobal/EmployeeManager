package com.sparta.sj.Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmployeeRecords {
    private Map<String, Employee> employeeMap = new HashMap<>();
    private int counter = 0;
    private Iterator it = employeeMap.entrySet().iterator();

    public EmployeeRecords(Map<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    public Map<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public Employee getEmployee(String key) {
        return employeeMap.get(key);
    }

    public Employee getNextEmployee(){
        Employee employee = null;
        if(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            employee = (Employee) pair.getValue();
        }
        return employee;
    }

    public Collection<Employee> getEmployees(){
        return employeeMap.values();
    }

    public boolean hasNextEmployee(){
        if(it.hasNext()) {
            return true;
        }
        return false;
    }
}
