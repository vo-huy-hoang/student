package com.example.employee.service.impl;

import com.example.employee.model.Department;
import com.example.employee.repository.impl.DepartmentRepository;
import com.example.employee.service.IDepartmentService;

import java.util.List;

public class DepartmentService implements IDepartmentService {
    private DepartmentRepository departmentRepository = new DepartmentRepository();
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
