package com.sparta.sj.Controller;

import com.sparta.sj.Model.CSVReader;
import com.sparta.sj.Model.DAO;
import com.sparta.sj.Model.EmployeeRecords;
import com.sparta.sj.Model.Timer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EmployeeManager {


    private Thread thread1;
    private Thread thread2;
    private Thread thread3;
    private Thread thread4;
    private static Logger logger = Logger.getLogger(EmployeeManager.class);

    public void sendToDataBase(){
        EmployeeRecords employeeRecords = getEmployees();
        DAO dao = new DAO(employeeRecords);

        dao.insertEmployeesToDatabase();

    }

    private EmployeeRecords getEmployees() {
        String filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/employee_records.csv";
        CSVReader reader = new CSVReader();
        reader.readToEmployeesMap(filepath);

        return reader.getEmployees();
    }


    public void insertWithThreads() {
        try {
            EmployeeRecords employeeRecords = getEmployees();
            DAO d = new DAO(employeeRecords);

            Runnable run1 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.first();
                    }catch(Exception e){}

                }
            };

            Runnable run2 = new Runnable() {
                @Override
                public void run() {
                    try{
                        d.second();
                    }catch(Exception e){}

                }
            };


            ExecutorService es = Executors.newCachedThreadPool();
            es.execute(run1);
            es.execute(run2);
            es.shutdown();
            boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
            if(finished){
                Timer.setEnd(System.nanoTime());
                logger.info("runtime: "+Timer.getRuntime());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertWithFiveThreads() {
        try {
            EmployeeRecords employeeRecords = getEmployees();
            DAO d = new DAO(employeeRecords);

            Runnable run1 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.firstOfFive();
                    }catch(Exception e){}

                }
            };

            Runnable run2 = new Runnable() {
                @Override
                public void run() {
                    try{
                        d.secOfFive();
                    }catch(Exception e){}

                }
            };

            Runnable run3 = new Runnable() {
                @Override
                public void run() {
                    try{
                        d.thirdOfFive();
                    }catch(Exception e){}

                }
            };

            Runnable run4 = new Runnable() {
                @Override
                public void run() {
                    try{
                        d.fourthOfFive();
                    }catch(Exception e){}

                }
            };

            Runnable run5 = new Runnable() {
                @Override
                public void run() {
                    try{
                        d.fifthOfFive();
                    }catch(Exception e){}

                }
            };


            ExecutorService es = Executors.newCachedThreadPool();
            es.execute(run1);
            es.execute(run2);
            es.execute(run3);
            es.execute(run4);
            es.execute(run5);
            es.shutdown();
            boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
            if(finished){
                d.closeConnection();
                Timer.setEnd(System.nanoTime());
                logger.info("Runtime: "+Timer.getRuntime());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

