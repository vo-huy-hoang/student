package com.example.employee.repository;

import com.example.employee.model.Department;
import com.example.employee.repository.impl.DepartmentRepository;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
}
