package com.example.employee.dto;

import java.time.LocalDate;

public class EmployeeSearchDTO {
    private String name="";
    private String fromBirthDay;
    private String toBirthDay;
    private String gender;
    private String salary;
    private String phoneNumber="";
    private String departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromBirthDay() {
        return fromBirthDay;
    }

    public void setFromBirthDay(String fromBirthDay) {
        this.fromBirthDay = fromBirthDay;
    }

    public String getToBirthDay() {
        return toBirthDay;
    }

    public void setToBirthDay(String toBirthDay) {
        this.toBirthDay = toBirthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
