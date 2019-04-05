package com.sparta.sj.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Employee {

    private String emp_id;
    private String name_prefix;
    private String first_name;
    private String last_name;
    private String middle_initial;
    private String gender;
    private String email;
    private double salary;
    private LocalDate date_of_birth;
    private LocalDate date_of_joining;
    private boolean is_duplicate;
//    private DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
//                                                                        .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
//                                                                        .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
//                                                                        .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
//                                                                        .toFormatter();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Employee(String[] values){
//        try {
            this.emp_id = values[0];
            this.name_prefix = values[1];
            this.first_name = values[2];
            this.middle_initial = values[3];
            this.last_name = values[4];
            this.gender = values[5];
            this.email = values[6];
            this.date_of_birth = LocalDate.parse(values[7], formatter);
            this.date_of_joining = LocalDate.parse(values[8], formatter);
            this.salary = Double.parseDouble(values[9]);

//        }catch(DateTimeParseException e){

//        }
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getName_prefix() {
        return name_prefix;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_initial() {
        return middle_initial;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public LocalDate getDate_of_joining() {
        return date_of_joining;
    }

    public boolean isIs_duplicate() {
        return is_duplicate;
    }

    public void setIs_duplicate(boolean is_duplicate) {
        this.is_duplicate = is_duplicate;
    }
}
