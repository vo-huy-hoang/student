package com.example.student.service.impl;

import com.example.student.dto.StudentDTO;
import com.example.student.dto.StudentSearchDTO;
import com.example.student.model.Student;
import com.example.student.repository.IStudentRepository;
import com.example.student.repository.impl.StudentRepository;
import com.example.student.service.IStudentService;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StudentService implements IStudentService {
    private IStudentRepository studentRepository = new StudentRepository();

    @Override
    public List<StudentDTO> search(StudentSearchDTO studentSearchDTO) throws SQLException {
        // tránh trường hợp người dùng đi vào màn hình list (chưa search)
        if (studentSearchDTO.getName() == null) {
            studentSearchDTO.setName("");
        }
        if ("".equals(studentSearchDTO.getFromScore())) {
            studentSearchDTO.setFromScore(null);
        }
        if ("".equals(studentSearchDTO.getToScore())) {
            studentSearchDTO.setToScore(null);
        }
        return studentRepository.search(studentSearchDTO);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public void create(Student student) {
        studentRepository.create(student);
    }

    @Override
    public void validate(HttpServletRequest request, Map<String, String> massageError) {
        String nameStr = request.getParameter("name");
        String scoreStr = request.getParameter("score");
        String clazzIdStr = request.getParameter("clazzId");

        if (nameStr.trim().equals("")) {
            massageError.put("name", "Tên bắt buộc nhập");
        } else if(!nameStr.matches("[a-zA-ZÀ-ỹ\\s]+")) {
            massageError.put("name", "Tên chỉ chứa khoảng cách hoặc chữ cái");
        }
        if (scoreStr.trim().equals("")) {
            massageError.put("score", "Điểm bắt buộc nhập");
        }
        if (clazzIdStr.equals("")) {
            massageError.put("clazzId", "Điểm bắt buộc nhập");
        }
    }
}
