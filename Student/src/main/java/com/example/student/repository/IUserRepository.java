package com.example.student.repository;

import com.example.student.model.User;

import java.util.List;

public interface IUserRepository {
    User findByUsername(String username);

    List<String> findRolesByUsername(String username);
}
