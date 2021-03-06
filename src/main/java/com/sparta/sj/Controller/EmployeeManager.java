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

    private Logger logger = Logger.getLogger(EmployeeManager.class);

    //abstractly sending CSV to Database
    public void sendCSVToDatabase() {
        insertWithFiveThreads();
    }

    // sends data to the database without threads
    private void insertWithoutThreads() {
        EmployeeRecords employeeRecords = getEmployees();
        DAO dao = new DAO(employeeRecords);
        dao.insertEmployeesToDatabase();
        dao.closeConnection();
    }


    //Gets employees from CSV file validates, verifies and creates records object to send to database
    private EmployeeRecords getEmployees() {
        String filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/EmployeeRecordsLarge.csv";
        CSVReader reader = new CSVReader(filepath);
        return reader.getEmployees();
    }

    // runs 2 threads simultaneously to send data to the database
    private void insertWithTwoThreads() {
        try {
            boolean finished;
            EmployeeRecords employeeRecords = getEmployees();
            DAO d = new DAO(employeeRecords);

            Runnable run1 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.first();
                    } catch (Exception e) {
                    }

                }
            };

            Runnable run2 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.second();
                    } catch (Exception e) {
                    }

                }
            };

            ExecutorService es = Executors.newCachedThreadPool();
            es.execute(run1);
            es.execute(run2);
            es.shutdown();

            finished = es.awaitTermination(1, TimeUnit.MINUTES);
            if (finished) {
                d.closeConnectionTwoThreaded();
                Timer.setEnd(System.nanoTime());
                logger.info("Runtime: " + Timer.getRuntime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // runs 5 threads simultaneaously to send to the database
    private void insertWithFiveThreads() {
        try {
            boolean finished;
            EmployeeRecords employeeRecords = getEmployees();
            DAO d = new DAO(employeeRecords);

            Runnable run1 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.firstOfFive();
                    } catch (Exception e) {
                    }

                }
            };

            Runnable run2 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.secOfFive();
                    } catch (Exception e) {
                    }

                }
            };

            Runnable run3 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.thirdOfFive();
                    } catch (Exception e) {
                    }

                }
            };

            Runnable run4 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.fourthOfFive();
                    } catch (Exception e) {
                    }

                }
            };

            Runnable run5 = new Runnable() {
                @Override
                public void run() {
                    try {
                        d.fifthOfFive();
                    } catch (Exception e) {
                    }

                }
            };


            ExecutorService es = Executors.newCachedThreadPool();
            es.execute(run1);
            es.execute(run2);
            es.execute(run3);
            es.execute(run4);
            es.execute(run5);
            es.shutdown();

            finished = es.awaitTermination(1, TimeUnit.MINUTES);
            if (finished) {
                d.closeConnectionFiveThreaded();
                Timer.setEnd(System.nanoTime());
                logger.info("Runtime: " + Timer.getRuntime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

