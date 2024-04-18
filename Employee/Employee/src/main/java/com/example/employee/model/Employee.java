package com.example.employee.model;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private LocalDate birthDay;
    private boolean gender;
    private double salary;
    private String phoneNumber;
    private int departmentId;

    public Employee() {
    }

    public Employee(String name, LocalDate birthDay, boolean gender, double salary, String phoneNumber, int departmentId) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
    }

    public Employee(int id, String name, LocalDate birthDay, boolean gender, double salary, String phoneNumber, int departmentId) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
