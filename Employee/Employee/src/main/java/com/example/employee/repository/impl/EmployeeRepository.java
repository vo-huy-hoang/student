package com.example.employee.repository.impl;

import com.example.employee.dto.EmployeeDTO;
import com.example.employee.dto.EmployeeSearchDTO;
import com.example.employee.model.Employee;
import com.example.employee.repository.IEmployeeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {

    @Override
    public List<EmployeeDTO> search(EmployeeSearchDTO employeeSearchDTO) {
        List<EmployeeDTO> employeeList = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder("select e.id, e.name, e.birthDay, e.gender, e.salary, e.phone_number, e.department_id, d.name as department_name from employee as e left join department d on e.department_id = d.id ");
            // tìm kiếm theo tên
            stringBuilder.append(" where e.name like concat('%', ?, '%')");

            // tìm kiếm theo sdt
            stringBuilder.append(" and e.phone_number like concat('%', ?, '%')");

            // tìm kiếm theo ngày sinh
            if (employeeSearchDTO.getFromBirthDay() != null || employeeSearchDTO.getToBirthDay() != null) {
                if (employeeSearchDTO.getFromBirthDay() == null) {
                    stringBuilder.append(String.format(" and e.birthDay <= '%s'", employeeSearchDTO.getToBirthDay()));
                } else if (employeeSearchDTO.getToBirthDay() == null) {
                    stringBuilder.append(String.format(" and e.birthDay >= '%s'", employeeSearchDTO.getFromBirthDay()));
                } else {
                    stringBuilder.append(String.format(" and (e.birthDay between '%s' and '%s')", employeeSearchDTO.getFromBirthDay(), employeeSearchDTO.getToBirthDay()));
                }
            }
            // tìm kiếm theo giới
            if (employeeSearchDTO.getGender() != null && !employeeSearchDTO.getGender().isEmpty()) {
                stringBuilder.append(String.format(" and e.gender = %s", employeeSearchDTO.getGender()));
            }

            // tìm kiếm theo lương
            if ("lt5".equals(employeeSearchDTO.getSalary())) {
                stringBuilder.append(" and (e.salary < 5000000");
            } else if ("5-10".equals(employeeSearchDTO.getSalary())) {
                stringBuilder.append(" and (e.salary >= 5000000 and e.salary < 10000000");
            } else if ("10-15".equals(employeeSearchDTO.getSalary())) {
                stringBuilder.append(" and (e.salary >= 10000000 and e.salary < 15000000");
            } else if ("gt5".equals(employeeSearchDTO.getSalary())) {
                stringBuilder.append(" and (e.salary >= 15000000");
            }

            // tìm kiếm theo bộ p
            if (employeeSearchDTO.getDepartmentId() != null && !employeeSearchDTO.getDepartmentId().isEmpty()) {
                stringBuilder.append(String.format(" and e.department_id = %s", employeeSearchDTO.getDepartmentId()));
            }
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    stringBuilder.toString()
            );
            preparedStatement.setString(1, employeeSearchDTO.getName());
            preparedStatement.setString(2, employeeSearchDTO.getPhoneNumber());

            ResultSet resultSet = preparedStatement.executeQuery();
            EmployeeDTO employeeDTO;
            while (resultSet.next()) {
                employeeDTO = new EmployeeDTO();
                employeeDTO.setId(resultSet.getInt("id"));
                employeeDTO.setName(resultSet.getString("name"));
                employeeDTO.setBirthDay(LocalDate.parse(resultSet.getString("birthDay")));
                employeeDTO.setGender(resultSet.getBoolean("gender"));
                employeeDTO.setSalary(resultSet.getDouble("salary"));
                employeeDTO.setPhoneNumber(resultSet.getString("phone_number"));
                employeeDTO.setDepartmentName(resultSet.getString("department_name"));

                employeeList.add(employeeDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public Employee findById(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "select id, name, birthDay, gender, salary, phone_number, department_id from employee where id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee;
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId((resultSet.getInt("id")));
                employee.setName(resultSet.getString("name"));
                employee.setBirthDay(LocalDate.parse(resultSet.getString("birthDay")));
                employee.setGender(resultSet.getBoolean("gender"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setDepartmentId(resultSet.getInt("department_id"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Employee employee) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "insert into employee(name, birthDay, gender, salary, phone_number, department_id) values (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getBirthDay().toString());
            preparedStatement.setBoolean(3, employee.getGender());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setInt(6, employee.getDepartmentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "update employee set name = ?, birthDay = ?, gender = ?, salary = ?, phone_number = ?, department_id = ? where id = ?"
            );
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getBirthDay().toString());
            preparedStatement.setBoolean(3, employee.getGender());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setInt(6, employee.getDepartmentId());
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "delete from employee where id = ?"
            );


            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee findByPhoneNumber(String phoneNumber) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(
                    "select id, name, birthDay, gender, salary, phone_number, department_id from employee where phone_number = ?"
            );
            preparedStatement.setString(1, phoneNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee;
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId((resultSet.getInt("id")));
                employee.setName(resultSet.getString("name"));
                employee.setBirthDay(LocalDate.parse(resultSet.getString("birthDay")));
                employee.setGender(resultSet.getBoolean("gender"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setDepartmentId(resultSet.getInt("department_id"));

                return employee;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
