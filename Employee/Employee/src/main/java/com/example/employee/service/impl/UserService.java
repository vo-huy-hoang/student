package com.example.employee.service.impl;

import com.example.employee.model.User;
import com.example.employee.repository.IUserRepository;
import com.example.employee.repository.impl.UserRepository;
import com.example.employee.service.IUserService;

import java.util.List;

public class UserService implements IUserService {
    private IUserRepository userRepository = new UserRepository();
    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public List<String> findRolesByUsername(String username) {
        return userRepository.findRolesByUsername(username);
    }

}
