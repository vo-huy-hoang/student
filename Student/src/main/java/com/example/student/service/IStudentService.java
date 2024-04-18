package com.example.student.service;

import com.example.student.dto.StudentDTO;
import com.example.student.dto.StudentSearchDTO;
import com.example.student.model.Student;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IStudentService {
    List<StudentDTO> search(StudentSearchDTO studentSearchDTO) throws SQLException;

    Student findById(int id);

    void create(Student student);

    void validate(HttpServletRequest request, java.util.Map<java.lang.String,java.lang.String> massageError);
}
