package com.example.employee.service.impl;

import com.example.employee.dto.EmployeeDTO;
import com.example.employee.dto.EmployeeSearchDTO;
import com.example.employee.model.Employee;
import com.example.employee.repository.IEmployeeRepository;
import com.example.employee.repository.impl.EmployeeRepository;
import com.example.employee.service.IEmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

public class EmployeeService implements IEmployeeService {
    private IEmployeeRepository employeeRepository = new EmployeeRepository();
    @Override
    public List<EmployeeDTO> search(EmployeeSearchDTO employeeSearchDTO) {
        return employeeRepository.search(employeeSearchDTO);
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void create(Employee employee) {
        employeeRepository.create(employee);
    }
    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    @Override
    public void delete(int id) {
        employeeRepository.delete(id);
    }

    @Override
    public void validate(HttpServletRequest request, Map<String, String> massageError) {

        String nameStr = request.getParameter("name");
        String birthDayStr = request.getParameter("birthDay");
        String genderStr = request.getParameter("gender");
        String salaryStr = request.getParameter("salary");

        if (nameStr.trim().equals("")) {
            massageError.put("name", "Tên bắt buộc nhập");
        } else if(!nameStr.matches("[a-zA-ZÀ-ỹ\\s]+")) {
            massageError.put("name", "Tên chỉ chứa khoảng cách hoặc chữ cái");
        }
        if (birthDayStr.trim().equals("")) {
            massageError.put("birthDay", "Ngày sinh bắt buộc nhập");
        }
        if (genderStr.trim().equals("")) {
            massageError.put("gender", "Giới tính bắt buộc chọn");
        }
        if (salaryStr.trim().equals("")) {
            massageError.put("salary", "Lương bắt buộc nhập");
        }
    }

    @Override
    public void validateNumberPhoneExists(HttpServletRequest request, HttpServletResponse response) {

    }
}
