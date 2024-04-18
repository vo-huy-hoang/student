package com.example.employee.service;

import com.example.employee.model.User;

import java.util.List;

public interface IUserService {
    User findByUserName(String username);
    List<String> findRolesByUsername(String username);

}
