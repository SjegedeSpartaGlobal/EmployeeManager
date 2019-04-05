package com.sparta.sj.Model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.format.DateTimeParseException;

public class CSVReader {

    private HashMap<String, Employee> employeeMap = new HashMap<>();
    private HashMap<String, Employee> employeeDupMap = new HashMap<>();
    private HashMap<String, Employee> unvalidatedEMap = new HashMap<>();
    private EmployeeRecords employees;
    private EmployeeRecords employeeDups;
    private EmployeeRecords employeeUnVal;

    public void readToEmployeesMap(String filepath){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                readEmployeeHelper(line.split(","));
            }
        }catch(IOException e) {

        }
    }

    public EmployeeRecords getEmployees() {
        employees = new EmployeeRecords(employeeMap);
        return employees;
    }

    public EmployeeRecords getEmployeeDups() {
        employeeDups = new EmployeeRecords(employeeDupMap);
        return employeeDups;
    }

    public EmployeeRecords getUnvalidated() {
        employeeUnVal = new EmployeeRecords(unvalidatedEMap);
        return employeeUnVal;
    }

    private Employee createEmployee(String[] values) {
        Employee employee = new Employee(values);
        return employee;
    }

    private void readEmployeeHelper(String[] values){
        Employee nextEmployee = createEmployee(values);
        Validator validator = new Validator();
        if(validator.validateRecord(values)) {
            if (!employeeMap.containsKey(values[0])) {
                employeeMap.put(values[0], nextEmployee);
            } else {
                addToDuplicate(values[0], nextEmployee);
            }
        }else{
            unvalidatedEMap.put(values[0], nextEmployee);
        }
    }

    private void addToDuplicate(String ID, Employee nextEmployee){
        employeeDupMap.put(ID, nextEmployee);
    }


}
