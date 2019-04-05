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

    private boolean validateIsEmpId(String input){
        if(input.matches("\\d{1,10}")){
            return true;
        }
        return false;
    }

    private boolean validateIsName(String input){
        if(input.matches("^[A-Z]+((([\\'\\,\\.\\-][a-zA-Z])?[a-zA-Z]*)[\\.]*)*$")){
            return true;
        }
        return false;
    }

    private boolean validateIsGender(String input){
        if(input.equals("F")||input.equals("M")){
            return true;
        }
        return false;
    }

    private boolean validateIsEmail(String input){
        if(input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            return true;
        }
        return false;
    }

    private boolean validateIsSalary(String input){
        if(input.matches("^((100)|(\\d{0,10}))$")){
            return true;
        }
        return false;
    }

    private boolean validateIsPastDate(String input){
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

    private void setFlags(String[] values){
        isEmpId = validateIsEmpId(values[0]);
        isNamePrefix = validateIsName(values[1]);
        isFirstName = validateIsName(values[2]);
        isMiddleInitial = validateIsName(values[3]);
        isLastName = validateIsName(values[4]);
        isGender = validateIsGender(values[5]);
        isEmail = validateIsEmail(values[6]);
        isDateOfBirth = validateIsPastDate(values[7]);
        isDateOfJoining = validateIsPastDate(values[8]);
        isSalary = validateIsSalary(values[9]);
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
