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
    private int rem;
    private int partition;
    private int firstPart;
    private int secPart;
    private int thirdPart;
    private int fourthPart;
    private int fifthPart;
    private boolean firstDone;
    private boolean secDone;
    private boolean thirdDone;
    private boolean fourthDone;
    private boolean fifthDone;


    public DAO( EmployeeRecords records){
        try {
            Timer.setStart(System.nanoTime());
            this.records = records;
            this.employeeList = new ArrayList<>(this.records.getEmployees());
            emp_length = employeeList.size();
            connection = DriverManager.getConnection(MY_SQL + TimeZone.getDefault().getID());
            connection.setAutoCommit(false);
            setPartition();
            firstPart = rem + partition;
            secPart = firstPart + partition;
            thirdPart = secPart + partition;
            fourthPart = thirdPart + partition;
            fifthPart = fourthPart + partition;
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

    public synchronized void firstOfFive(){
        for (int i = 0; i < firstPart; i++) {
            addEmployee(employeeList.get(i));
        }

        firstDone = true;
    }

    public synchronized void secOfFive(){
        for (int i = firstPart; i < secPart; i++) {
            addEmployee(employeeList.get(i));
        }

        secDone = true;
    }

    public synchronized void thirdOfFive(){
        for (int i = secPart; i < thirdPart; i++) {
            addEmployee(employeeList.get(i));
        }

        thirdDone = true;
    }

    public synchronized void fourthOfFive(){
        for (int i = thirdPart; i < fourthPart; i++) {
            addEmployee(employeeList.get(i));
        }

        fourthDone = true;
    }

    public synchronized void fifthOfFive(){
        for (int i = fourthPart; i < fifthPart; i++) {
            addEmployee(employeeList.get(i));
        }

        fifthDone = true;
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

    private void setRem(){
        rem = emp_length%5;

    }

    private void setPartition(){
        setRem();
        int newLength = emp_length - rem;
        partition = emp_length/5;
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

    public void closeConnection(){
        try {
            if(firstDone&&secDone&&thirdDone&&fourthDone&&fifthDone) {
                this.connection.commit();
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}