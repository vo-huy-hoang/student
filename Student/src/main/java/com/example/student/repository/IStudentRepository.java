package com.example.student.repository;

import com.example.student.dto.StudentDTO;
import com.example.student.dto.StudentSearchDTO;
import com.example.student.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentRepository {
     List<StudentDTO> search(StudentSearchDTO studentSearchDTO) throws SQLException;

     Student findById(int id);

     void create(Student student);
}
