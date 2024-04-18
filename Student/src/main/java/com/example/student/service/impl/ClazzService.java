package com.example.student.service.impl;

import com.example.student.model.Clazz;
import com.example.student.repository.IClazzRepository;
import com.example.student.repository.impl.ClazzRepository;
import com.example.student.service.IClazzService;

import java.util.List;

public class ClazzService implements IClazzService {
    private IClazzRepository clazzRepository = new ClazzRepository();
    @Override
    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }
}
