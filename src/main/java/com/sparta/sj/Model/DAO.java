package com.sparta.sj.Model;

import java.sql.SQLException;
import java.util.*;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DAO{
    private String addEmployees = "INSERT INTO employees values (?,?,?,?,?,?,?,?,?,?)";
    private final String MY_SQL = "jdbc:mysql://localhost:3306/employees?user=root&password=1992Megamind&useSSL=false&serverTimezone=";
    private Collection<Employee> employees;
    private List<Employee> employeeList;
    private EmployeeRecords records;
    private Connection connection;
    private int emp_length;
    private int mid;

    public DAO( EmployeeRecords records){
        try {
            Timer.setStart(System.nanoTime());
            this.records = records;
            this.employeeList = new ArrayList<>(this.records.getEmployees());
            this.emp_length = this.employeeList.size();
            this.mid = getMid();
            this.connection = DriverManager.getConnection(this.MY_SQL + TimeZone.getDefault().getID());
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEmployeesToDatabase() {
        this.employees = records.getEmployees();
        for (Employee employee: employees) {
            addEmployee(employee);
        }
    }

    public synchronized void first(){
        int end = mid;

        for (int i = 0; i < end; i++) {
            addEmployee(employeeList.get(i));
        }
    }

    public synchronized void second(){
        int start = mid;
        int end = emp_length;

        for (int i = start; i < end; i++) {
            addEmployee(employeeList.get(i));
        }

        closeConnection();
    }

    private int getMid(){
        int mid = 0;
        if(emp_length%2!=0){
            mid = (emp_length/2) + 1;
        }else{
            mid = emp_length/2;
        }
        return mid;
    }

    private void addEmployee(Employee employee) {
        try {
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
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }  catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try {
            this.connection.commit();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}