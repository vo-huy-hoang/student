package com.example.employee.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.getSession().invalidate();
        Cookie cookie = new Cookie("remember-me", "");
        cookie.setMaxAge(0); // xoá cookie
        response.addCookie(cookie);

        response.sendRedirect("/login?message=" + URLEncoder.encode("Đăng xuất thành công", "UTF-8"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }
}
