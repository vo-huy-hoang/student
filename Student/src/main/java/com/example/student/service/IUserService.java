package com.example.student.service;

import com.example.student.model.User;

import java.util.List;

public interface IUserService {
    User findByUsername(String username);
    List<String> findRolesByUsername(String username);
}
