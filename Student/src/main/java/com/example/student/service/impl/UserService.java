package com.example.student.service.impl;

import com.example.student.model.User;
import com.example.student.repository.IUserRepository;
import com.example.student.repository.impl.UserRepository;
import com.example.student.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private IUserRepository userRepository = new UserRepository();
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public List<String> findRolesByUsername(String username) {
        return userRepository.findRolesByUsername(username);
    }
}
