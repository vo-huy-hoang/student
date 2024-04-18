package com.example.employee.repository;

import com.example.employee.dto.EmployeeDTO;
import com.example.employee.dto.EmployeeSearchDTO;
import com.example.employee.model.Employee;

import java.util.List;

public interface IEmployeeRepository {
    List<EmployeeDTO> search(EmployeeSearchDTO employeeSearchDTO);

    Employee findById(int id);

    void create(Employee employee);

    void update(Employee employee);

    void delete(int id);
    Employee findByPhoneNumber(String phoneNumber);
}
