package com.sparta.sj.Model;

import java.sql.SQLException;
import java.util.*;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO{
    private String addEmployees = "INSERT INTO employees values (?,?,?,?,?,?,?,?,?,?)";
    private final String MY_SQL = "jdbc:mysql://localhost:3306/employees?user=root&password=1992Megamind&useSSL=false";
    private Collection<Employee> employees;
    private List<Employee> employeeList;
    private int emp_length;

    public long getStartT() {
        return startT;
    }

    public long getEndT() {
        return endT;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    private long startT;
    private long endT;
    private boolean  hasFinished;


    public void EmployeeInsert(EmployeeRecords records) {
        this.employees = records.getEmployees();
        for (Employee employee: employees) {
            addEmployee(employee);
        }
    }

    public void first(){
        startT = System.nanoTime();
        int end = getMid();

        for (int i = 0; i < end; i++) {
            addEmployee(employeeList.get(i));
        }
    }

    public void second(){
        int start = getMid();
        int end = emp_length;

        for (int i = start; i < end; i++) {
            addEmployee(employeeList.get(i));
        }
        endT = System.nanoTime();
        hasFinished = true;
    }

    public void setThreads( EmployeeRecords records){
        employeeList = getEmployeesList(records);
        emp_length = setLength(employeeList.size());
    }


    public int getMid(){
        int mid = 0;
        if(emp_length%2!=0){
            mid = (emp_length/2) + 1;
        }else{
            mid = emp_length/2;
        }
        return mid;
    }

    public List<Employee> getEmployeesList(EmployeeRecords records) {
        this.employeeList = new ArrayList<>(records.getEmployees());
        return this.employeeList;
    }

    public int setLength(int length){
        this.emp_length = length;
        return this.emp_length;
    }

    public void addEmployee(Employee employee) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(MY_SQL);
            PreparedStatement statement = connection.prepareStatement(addEmployees);
            statement.setString(1, employee.getEmp_id());
            statement.setString(2, employee.getName_prefix());
            statement.setString(3, employee.getFirst_name());
            statement.setString(4, employee.getMiddle_initial());
            statement.setString(5, employee.getLast_name());
            statement.setString(6, employee.getGender());
            statement.setString(7, employee.getEmail());
            statement.setDate(8, Date.valueOf(employee.getDate_of_birth()));
            statement.setDate(9, Date.valueOf(employee.getDate_of_joining()));
            statement.setInt(10,(int) employee.getSalary());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }  catch (NullPointerException e) {
            e.printStackTrace();
        }finally{
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}