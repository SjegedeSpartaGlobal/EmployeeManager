package com.sparta.sj.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private boolean isEmpId = false;
    private boolean isNamePrefix = false;
    private boolean isFirstName = false;
    private boolean isMiddleInitial = false;
    private boolean isLastName = false;
    private boolean isGender = false;
    private boolean isEmail = false;
    private boolean isDateOfJoining = false;
    private boolean isDateOfBirth = false;
    private boolean isSalary = false;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    //checks all the fields in the record to pass the whole record as validated
    public boolean validateRecord(String[] values){
        setFlags(values);
        List<Boolean> flags = getFlags();
        boolean next = false;

        for(int i=0; i<flags.size(); i++){
            next = flags.get(i);
            if(!next){
                return false;
            }
        }
        return true;
    }

    private boolean validateEmpId(String input){
        if(input.matches("\\d{1,10}")){
            return true;
        }
        return false;
    }

    private boolean validateName(String input){
        if(input.matches("^[A-Z]+((([\\'\\,\\.\\-][a-zA-Z])?[a-zA-Z]*)[\\.]*)*$")){
            return true;
        }
        return false;
    }

    private boolean validateGender(String input){
        if(input.equals("F")||input.equals("M")){
            return true;
        }
        return false;
    }

    private boolean validateEmail(String input){
        if(input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            return true;
        }
        return false;
    }

    private boolean validateSalary(String input){
        if(input.matches("^((100)|(\\d{0,10}))$")){
            return true;
        }
        return false;
    }

    //Because date of birth and date of joining have to be date from the past
    private boolean validatePastDate(String input){
        LocalDate today = LocalDate.now();
        LocalDate inputDate = LocalDate.parse(input, formatter);
        if(isDateFormat(input)){
            if(inputDate.isBefore(today)){
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isDateFormat(String input){
        try{
           LocalDate.parse(input, formatter);
           return true;
        }
        catch(DateTimeParseException e){
            return false;
        }
    }

    //sets a boolean for each field depending on whether the field has passed validation
    private void setFlags(String[] values){
        isEmpId = validateEmpId(values[0]);
        isNamePrefix = validateName(values[1]);
        isFirstName = validateName(values[2]);
        isMiddleInitial = validateName(values[3]);
        isLastName = validateName(values[4]);
        isGender = validateGender(values[5]);
        isEmail = validateEmail(values[6]);
        isDateOfBirth = validatePastDate(values[7]);
        isDateOfJoining = validatePastDate(values[8]);
        isSalary = validateSalary(values[9]);
    }

    private List<Boolean> getFlags(){
        List<Boolean> valList = new ArrayList<Boolean>();
        valList.add(isEmpId);
        valList.add(isNamePrefix);
        valList.add(isFirstName);
        valList.add(isMiddleInitial);
        valList.add(isLastName);
        valList.add(isGender);
        valList.add(isEmail);
        valList.add(isDateOfBirth);
        valList.add(isDateOfJoining);
        valList.add(isSalary);

        return valList;
    }
}
