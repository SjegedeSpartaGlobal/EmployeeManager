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
        EmployeeManager em_t = new EmployeeManager();
        em_t.insertWithFiveThreads();
    }
}

