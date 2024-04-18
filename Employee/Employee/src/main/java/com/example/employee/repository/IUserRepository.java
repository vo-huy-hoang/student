package com.example.employee.repository;

import com.example.employee.model.User;

import java.util.List;

public interface IUserRepository {
    User findByUserName(String username);
    List<String> findRolesByUsername(String username);
}
