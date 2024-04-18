package com.example.employee.service;

import com.example.employee.dto.EmployeeDTO;
import com.example.employee.dto.EmployeeSearchDTO;
import com.example.employee.model.Employee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {
    List<EmployeeDTO> search(EmployeeSearchDTO employeeSearchDTO);

    Employee findById(int id);

    void create(Employee employee);

    void update(Employee employee);

    void delete(int id);

    void validate(HttpServletRequest request, Map<String, String> massageError);

    void validateNumberPhoneExists(HttpServletRequest request, HttpServletResponse response);
}
