package com.example.student.repository.impl;

import com.example.student.dto.StudentDTO;
import com.example.student.dto.StudentSearchDTO;
import com.example.student.model.Student;
import com.example.student.repository.IStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    @Override
    public List<StudentDTO> search(StudentSearchDTO studentSearchDTO){
        List<StudentDTO> studentList = new ArrayList<>();
        try {
            // stringBuilder để tạo chuỗi query (cho tiện)
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select s.id, s.name as name, s.score, c.name as clazz_name from student s inner join clazz c on s.clazz_id = c.id\n" +
                    "where s.name like concat('%', ?, '%') "); // tìm theo tên gần đúng
            // tìm kiếm theo điểm
            if (studentSearchDTO.getFromScore() != null || studentSearchDTO.getToScore() != null) {
                if (studentSearchDTO.getFromScore() == null) { // không nhập from => nhập t0
                    stringBuilder.append(String.format("and s.score <= %s ", studentSearchDTO.getToScore()));
                } else if (studentSearchDTO.getToScore() == null) { // không nhập to => nhập from
                    stringBuilder.append(String.format("and s.score >= %s ", studentSearchDTO.getFromScore()));
                } else { // nhập cả 2
                    stringBuilder.append(String.format(" and (s.score between %s and %s) ", studentSearchDTO.getFromScore()));
                }
            }
            // tìm kiếm theo lớp
            if (studentSearchDTO.getClazzId() != null && !"".equals(studentSearchDTO.getClazzId())) {
                stringBuilder.append(String.format("and s.clazz_id = %s ", studentSearchDTO.getClazzId()));
            }
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    stringBuilder.toString()
            );
            preparedStatement.setString(1, studentSearchDTO.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            StudentDTO studentDTO;
            while (resultSet.next()) {
                studentDTO = new StudentDTO();
                studentDTO.setId(resultSet.getInt("id"));
                studentDTO.setName(resultSet.getString("name"));
                studentDTO.setScore(resultSet.getDouble("score"));
                studentDTO.setClazzName(resultSet.getString("clazz_name"));

                studentList.add(studentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return studentList;
    }

    @Override
    public Student findById(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "select id, name, score from student where id = ?"
            );

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Student student;
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setScore(resultSet.getDouble("score"));

                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Student student) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "insert into student (name, score, clazz_id) values (?, ?, ?)"
            );

            preparedStatement.setString(1, student.getName());
            preparedStatement.setDouble(2, student.getScore());
            preparedStatement.setDouble(3, student.getClazzId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
