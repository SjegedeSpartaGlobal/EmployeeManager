package com.sparta.sj.Controller;

import com.sparta.sj.Model.CSVReader;
import com.sparta.sj.Model.DAO;
import com.sparta.sj.Model.Employee;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.sql.Date;

public class Starter {

    private static Logger logger = Logger.getLogger(Starter.class);

    public static void main(String[] args) {
//        long start = System.nanoTime();
//        EmployeeManager em = new EmployeeManager();
//        em.sendToDataBase();
//        long end = System.nanoTime();
//
//        System.out.println("runtime: "+(new BigDecimal(end - start).movePointLeft(9)));

        long start_t = System.nanoTime();
        EmployeeManager em_t = new EmployeeManager();
        em_t.insertWithThreads();

//        logger.trace("runtime with threads: " + (new BigDecimal(end_t - start_t).movePointLeft(9)));
//        DecimalFormat df = new DecimalFormat("###,###,###");
//        System.out.println(df.format(end_t - start_t));

//        System.out.println(23%5);


    }
}

