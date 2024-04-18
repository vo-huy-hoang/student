package com.example.employee.controller;

import com.example.employee.dto.EmployeeSearchDTO;
import com.example.employee.model.Employee;
import com.example.employee.service.IDepartmentService;
import com.example.employee.service.IEmployeeService;
import com.example.employee.service.impl.DepartmentService;
import com.example.employee.service.impl.EmployeeService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeServlet", value = "/employees")
public class EmployeeServlet extends HttpServlet {
    private IEmployeeService employeeService = new EmployeeService();
    private IDepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            case "view":
                viewEmployee(request, response);
                break;
            default:
                listEmployees(request, response);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("departmentList", departmentService.findAll());
        request.getRequestDispatcher("employee/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            request.getRequestDispatcher("error-404.jsp").forward(request, response);
        } else {
            request.setAttribute("id", employee.getId());
            request.setAttribute("name", employee.getName());
            request.setAttribute("birthDay", employee.getBirthDay());
            request.setAttribute("gender", employee.getGender());
            request.setAttribute("salary", employee.getSalary());
            request.setAttribute("phoneNumber", employee.getPhoneNumber());
            request.setAttribute("departmentId", employee.getDepartmentId());
            request.setAttribute("departmentList", departmentService.findAll());

            request.getRequestDispatcher("employee/edit.jsp").forward(request, response);;
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            request.getRequestDispatcher("error-404.jsp").forward(request, response);
        } else {
            employeeService.delete(id);
            response.sendRedirect("employees?message=" + URLEncoder.encode("Xoá thành công", "UTF8"));
        }
    }

    private void viewEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            request.getRequestDispatcher("error-404.jsp").forward(request, response);
        } else {
            request.setAttribute("employee", employee);
            request.setAttribute("departmentList", departmentService.findAll());
            request.getRequestDispatcher("employee/view.jsp").forward(request, response);
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeSearchDTO employeeSearchDTO = new EmployeeSearchDTO();

        if (request.getParameter("name") != null) {
            employeeSearchDTO.setName(request.getParameter("name"));
        }
        employeeSearchDTO.setFromBirthDay(request.getParameter("fromBirthDay"));
        employeeSearchDTO.setToBirthDay(request.getParameter("toBirthDay"));
        employeeSearchDTO.setGender(request.getParameter("gender"));
        employeeSearchDTO.setSalary(request.getParameter("salary"));

        if (request.getParameter("phoneNumber") != null) {
            employeeSearchDTO.setPhoneNumber(request.getParameter("phoneNumber"));
        }
        employeeSearchDTO.setDepartmentId(request.getParameter("departmentId"));

        request.setAttribute("employeeSearchDTO", employeeSearchDTO);
        request.setAttribute("employeeList", employeeService.search(employeeSearchDTO));
        request.setAttribute("departmentList", departmentService.findAll());
        request.getRequestDispatcher("employee/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createEmployee(request, response);
                break;
            case "edit":
                updateEmployee(request, response);
        }

    }
    private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // validate
        Map<String, String> massageError = new HashMap<>();
//        employeeService.validate(request, massageError);
        employeeService.validate(request, massageError);

        if (!massageError.isEmpty()) {
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("birthDay", request.getParameter("birthDay"));
            request.setAttribute("gender", request.getParameter("gender"));
            request.setAttribute("salary", request.getParameter("salary"));
            request.setAttribute("phoneNumber", request.getParameter("phoneNumber"));
            request.setAttribute("departmentId", request.getParameter("departmentId"));
            request.setAttribute("massageError", massageError);
            request.setAttribute("departmentList", departmentService.findAll());
            request.getRequestDispatcher("employee/create.jsp").forward(request, response);
            return;
        }

        String name = request.getParameter("name");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDay = LocalDate.parse(request.getParameter("birthDay"),formatter);
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phoneNumber = request.getParameter("phoneNumber");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        Employee employee = new Employee(name, birthDay, gender, salary, phoneNumber, departmentId);
        employeeService.create(employee);

        employeeService.create(employee);

        response.sendRedirect("/employees?message=" + URLEncoder.encode("Thêm mới thành công", "UTF8"));

    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            request.getRequestDispatcher("error-404.jsp");
        }
        // validate
        Map<String, String> massageError = new HashMap<>();
        employeeService.validate(request, massageError);
        if (!employee.getPhoneNumber().equals(request.getParameter("phoneNumber"))) {
            employeeService.validateNumberPhoneExists(request, response);
        }

        if (!massageError.isEmpty()) {
            request.setAttribute("id", request.getParameter("id"));
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("birthDay", request.getParameter("birthDay"));
            request.setAttribute("gender", request.getParameter("gender"));
            request.setAttribute("salary", request.getParameter("salary"));
            request.setAttribute("phoneNumber", request.getParameter("phoneNumber"));
            request.setAttribute("departmentId", request.getParameter("departmentId"));
            request.setAttribute("massageError", massageError);
            request.setAttribute("departmentList", departmentService.findAll());
            request.getRequestDispatcher("employee/create.jsp").forward(request, response);
            return;
        }

        String name = request.getParameter("name");
        LocalDate birthDay = LocalDate.parse(request.getParameter("birthDay"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phoneNumber = request.getParameter("phoneNumber");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        employee.setName(name);
        employee.setBirthDay(birthDay);
        employee.setGender(gender);
        employee.setSalary(salary);
        employee.setPhoneNumber(phoneNumber);
        employee.setDepartmentId(departmentId);

        employeeService.update(employee);

        response.sendRedirect("/employees?message=" + URLEncoder.encode("Cập nhật thành công", "UTF8"));
    }

}
