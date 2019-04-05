package com.sparta.sj;

import com.sparta.sj.Model.CSVReader;
import com.sparta.sj.Model.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ValidatorTest {

    private String validator_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/validator_test.csv";
    private String valid_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/validate_valid_test.csv";
    private String emp_id_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/emp_id_test.csv";
    private String np_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/name_prefix_test.csv";
    private String fname_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/first_name_test.csv";
    private String m_init_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/mid_init_test.csv";
    private String lname_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/last_name_test.csv";
    private String gender_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/gender_test.csv";
    private String email_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/email_test.csv";
    private String DOB_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/DOB_test.csv";
    private String DOJ_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/DOJ_test.csv";
    private String salary_filepath = "C:/Users/SJegede/IdeaProjects/EmployeeManager/resources/salary_test.csv";

    @Test
    public void testValidateRecord(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(validator_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidRecord(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(valid_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null){
                Assert.assertTrue((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateEmpID(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(emp_id_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateNamePrefix(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(emp_id_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null){
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateFirstName(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fname_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateMidInit(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(m_init_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLastName(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(lname_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateGender(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(gender_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateEmail(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(email_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateDOB(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(DOB_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateDOJ(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(DOB_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateSalary(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(salary_filepath))){
            reader.readLine();
            if((line = reader.readLine()) != null) {
                Assert.assertFalse((new Validator()).validateRecord(line.split(",")));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
