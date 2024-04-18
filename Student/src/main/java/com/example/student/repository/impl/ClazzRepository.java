package com.example.student.repository.impl;

import com.example.student.dto.StudentDTO;
import com.example.student.model.Clazz;
import com.example.student.repository.IClazzRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClazzRepository implements IClazzRepository {

    @Override
    public List<Clazz> findAll() {
        List<Clazz> clazzList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "select id, name from clazz"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            Clazz clazz;
            while (resultSet.next()) {
                clazz = new Clazz();
                clazz.setId(resultSet.getInt("id"));
                clazz.setName(resultSet.getString("name"));

                clazzList.add(clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return clazzList;
    }
}
