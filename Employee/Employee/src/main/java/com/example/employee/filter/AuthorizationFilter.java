package com.example.employee.filter;


import com.example.employee.dto.UserDetail;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "AuthorizationFilter", value = "/AuthorizationFilter")
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

        String action = httpRequest.getParameter("action");
        if(servletPath.equals("/employees") || action == null || action.equals("view")) {
            // Nếu như là màn hình employees nhưng không có action (Tức là màn hình list) thì có thể vào
            chain.doFilter(request, response); // Cho vào Servlet => Cho vào Servlet tương ứng (/employees vào Servlet Employee)
            return;
        }

        List<String> roles = ((UserDetail) session.getAttribute("userDetail")).getRoles();
        if(!roles.contains("admin")) { // Không chứa admin => THông báo không có quyền
            httpResponse.sendRedirect("/access-denied");
            return;
        }

        chain.doFilter(request, response);
    }
}
 
