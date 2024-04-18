package com.example.student.controller;

import com.example.student.dto.UserDetail;
import com.example.student.model.User;
import com.example.student.service.IUserService;
import com.example.student.service.impl.UserService;
import com.example.student.util.BCryptUtil;
import com.example.student.util.JWTUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private IUserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.findByUsername(username);
        if (user == null || !BCryptUtil.checkPassword(password, user.getPassword())) { // sai username hoặc password
            response.sendRedirect("/login?message=" + URLEncoder.encode("Tên Đăng Nhập hoặc Mật Khẩu sai", "UTF8"));
            return;
        }
        String rememberMe = request.getParameter("remember-me");
        if ("on".equals(rememberMe)) {
            String token = JWTUtil.generateTokenLogin(username);
            Cookie cookie = new Cookie("remember-me", token);
            cookie.setMaxAge(100 * 24 * 60 * 60); // 7 ngày 8->100
            response.addCookie(cookie);
        }
        HttpSession session = request.getSession();

        UserDetail userDetail = new UserDetail(user.getUsername(), userService.findRolesByUsername(user.getUsername()));
        // lưu username vào session
        session.setAttribute("username", user.getUsername()); // đặt tên gì cx dc
        response.sendRedirect("/student");
    }
}
