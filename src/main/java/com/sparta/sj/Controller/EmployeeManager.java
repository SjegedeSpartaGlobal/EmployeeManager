package com.sparta.sj.Controller;

import com.sparta.sj.Model.CSVReader;
import com.sparta.sj.Model.DAO;
import com.sparta.sj.Model.EmployeeRecords;

import java.sql.Connection;

public class EmployeeManager {


    private Thread thread1;
    private Thread thread2;
    private EmployeeRecords employeeRecords;

    public void sendToDataBase(){
        EmployeeRecords employeeRecords = getEmployees();
        DAO dao = new DAO();

        dao.EmployeeInsert(employeeRecords);

    }

    private EmployeeRecords getEmployees() {
        String filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/employee_records.csv";
        CSVReader reader = new CSVReader();
        reader.readToEmployeesMap(filepath);

        return reader.getEmployees();
    }


    public void insertWithThreads() {
        EmployeeRecords employeeRecords = getEmployees();
        DAO d = new DAO();
        d.setThreads(employeeRecords);
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                d.first();
            }
        };

        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                d.second();
            }
        };

        thread1 = new Thread(run1, "1st thread: ");
        thread2 = new Thread(run2, "2nd thread: ");

        thread1.start();
        thread2.start();
    }

    public boolean isFinished(){
        if(!thread1.isAlive()&&!thread2.isAlive()){
            return true;
        }
        return false;
    }
}

