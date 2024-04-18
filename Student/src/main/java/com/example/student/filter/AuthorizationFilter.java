package com.example.student.filter;


import com.example.student.dto.UserDetail;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();

        String servletPath = ((HttpServletRequest) request).getServletPath(); // /student

        String action = httpRequest.getParameter("action"); // action=create
        if(servletPath.equals("/student") && action == null) {
            // Nếu như là màn hình student nhưng không có action (Tức là màn hình list) thì có thể vào
            chain.doFilter(request, response); // Cho vào Servlet => Cho vào Servlet tương ứng (/student vào Servlet Student)
            return;
        }

        UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
        if(!userDetail.getRoles().contains("admin")) { // Không chứa admin => THông báo không có quyền
            httpResponse.sendRedirect("/access-denied");
            return;
        }

        chain.doFilter(request, response);
    }
}
 
