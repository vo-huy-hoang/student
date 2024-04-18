package com.example.student.controller;

import com.example.student.dto.StudentSearchDTO;
import com.example.student.model.Student;
import com.example.student.service.IClazzService;
import com.example.student.service.IStudentService;
import com.example.student.service.impl.ClazzService;
import com.example.student.service.impl.StudentService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private IStudentService studentService = new StudentService();
    private IClazzService clazzService = new ClazzService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            response.sendRedirect("/login?message=" + URLEncoder.encode("Bạn chưa đăng nhập", "UTF-8"));
            return;
        }

        String acion = request.getParameter("action");
        if (acion == null) {
            acion = "";
        }

        switch (acion) {
            case "create":
                request.setAttribute("clazzList", clazzService.findAll());
                request.getRequestDispatcher("student/create.jsp").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id")); // lấy id trên thanh url
                Student student = studentService.findById(id);
                request.setAttribute("student", student);
                request.getRequestDispatcher("student/edit.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("clazzList", clazzService.findAll());
                // lấy thông tin search
                StudentSearchDTO studentSearchDTO = new StudentSearchDTO();
                studentSearchDTO.setName(request.getParameter("name"));
                studentSearchDTO.setFromScore(request.getParameter("fromScore"));
                studentSearchDTO.setToScore(request.getParameter("toScore"));
                studentSearchDTO.setClazzId(request.getParameter("clazzId"));
                try {
                    request.setAttribute("studentList", studentService.search(studentSearchDTO));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                request.getRequestDispatcher("student/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        String acion = request.getParameter("action");
        if (acion == null) {
            acion = "";
        }

        switch (acion) {
            case "create":
                createStudent(request, response);
//                request.setAttribute("studentList", studentList);
//                request.getRequestDispatcher("student/list.jsp").forward(request, response);

                break;
            case "edit":
                eidtStudent(request, response);
//                request.setAttribute("studentList", studentList);
//                request.getRequestDispatcher("student/list.jsp").forward(request, response);
                response.sendRedirect("student?message=" + URLEncoder.encode("Chỉnh sửa thành công", "UTF8")); // mặc định là GET, ngắn gọn hơn 2 dòng trên
                break;
        }
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // validate
        Map<String, String> massageError = new HashMap<>();
        studentService.validate(request, massageError);

        if (!massageError.isEmpty()) {
            request.setAttribute("clazzList", clazzService.findAll());
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("score", request.getParameter("score"));
            request.setAttribute("massageError", massageError);
            request.getRequestDispatcher("student/create.jsp").forward(request, response);
            return;
        }
        Student student = new Student();
//        student.setId((int) (Math.random() * 10000));
        student.setName(request.getParameter("name"));
        student.setScore(Double.parseDouble(request.getParameter("score")));
        // add thêm c
        student.setClazzId(Integer.parseInt(request.getParameter("clazzId")));

        studentService.create(student);
        response.sendRedirect("student?message=" + URLEncoder.encode("Thêm mới thành công", "UTF8")); // mặc định là GET, ngắn gọn hơn 2 dòng trên
    }
    private void eidtStudent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        student.setName(request.getParameter("name"));
        student.setScore(Double.parseDouble(request.getParameter("score")));
    }

}
