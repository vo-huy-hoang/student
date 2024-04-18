package com.example.student.filter;


import com.example.student.dto.UserDetail;
import com.example.student.service.IUserService;
import com.example.student.service.impl.UserService;
import com.example.student.util.JWTUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private IUserService userService = new UserService();
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();

        String servletPath = ((HttpServletRequest) request).getServletPath();
        if ("/login".equals(servletPath)) {
            chain.doFilter(request, response); // cho vào Servlet
            return;
        }
        if (session.getAttribute("userDetail") == null) {
            // lấy cookie ở đây
            boolean isAutoLogin = false;
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // Kiểm tra cookie có tên "username"
                    if ("remember-me".equals(cookie.getName())) {
                        // Nếu tồn tại cookie "username", đánh dấu rằng người dùng đã đăng nhập
                        isAutoLogin = true;
                        String token = cookie.getValue();
                        // Lưu tên đăng nhập từ cookie vào session để duy trì trạng thái đăng nhập
                        String username = JWTUtil.getUserNameFromJwtToken(token);
                        UserDetail userDetail = new UserDetail(username, userService.findRolesByUsername(username));
                        //lưu username vào token
                        session.setAttribute("userDetail", userDetail);
                    }
                }
            }

            // Nếu người dùng chưa đăng nhập (không tồn tại session và không tự động đăng nhập từ cookie)
            if (!isAutoLogin) {
                // Chuyển hướng người dùng đến trang đăng nhập và kèm theo thông báo lỗi
                httpResponse.sendRedirect("login?message=" + URLEncoder.encode("Bạn chưa đăng nhập", "UTF-8")); // GET
                return;
            }
        }
        chain.doFilter(request, response); // cho vào Servlet
    }
}
 
