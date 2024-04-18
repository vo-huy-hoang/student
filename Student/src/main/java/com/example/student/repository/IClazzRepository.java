package com.example.student.repository;

import com.example.student.model.Clazz;

import java.util.List;

public interface IClazzRepository {
    List<Clazz> findAll();
}
