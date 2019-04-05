package com.sparta.sj;

import com.sparta.sj.Model.CSVReader;
import com.sparta.sj.Model.Employee;
import com.sparta.sj.Model.EmployeeRecords;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CSVReaderTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void testReadToEmployeesList(){
        CSVReader reader = new CSVReader();
        String filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/test.csv";
        reader.readToEmployeesMap(filepath);
        EmployeeRecords employee_map = reader.getEmployees();
        int size = employee_map.getEmployeeMap().size();
        Assert.assertEquals(13,size);
    }

    @Test
    public void testGetEmployeeDupMap(){
        CSVReader reader = new CSVReader();
        String filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/duplicate_test.csv";
        reader.readToEmployeesMap(filepath);
        EmployeeRecords employee_dups = reader.getEmployeeDups();
        int size = employee_dups.getEmployeeMap().size();

        Assert.assertTrue(size>0);
    }

    @Test
    public void testGetUnvalidatedEMap(){
        CSVReader reader = new CSVReader();
        String filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/validator_test.csv";
        reader.readToEmployeesMap(filepath);
        EmployeeRecords employeeUnV = reader.getUnvalidated();
        int size = employeeUnV.getEmployeeMap().size();

        Assert.assertTrue(size==5);
    }




}
