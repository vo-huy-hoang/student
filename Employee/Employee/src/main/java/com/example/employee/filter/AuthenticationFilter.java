package com.example.employee.filter;


import com.example.employee.dto.UserDetail;
import com.example.employee.service.IUserService;
import com.example.employee.service.impl.UserService;
import com.example.employee.util.JWTUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

//import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private final IUserService userService = new UserService();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        String urlPattern = ((HttpServletRequest) request).getServletPath();

        if ("/login".equals(urlPattern)) {
            chain.doFilter(request, response);
            return;
        }

        if (session.getAttribute("userDetail") == null) {
            boolean isAutoLogin = false;
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // Kiểm tra cookie có tên "username"
                    if ("remember-me".equals(cookie.getName())) {
                        // Nếu tồn tại cookie "username", đánh dấu rằng người dùng đã đăng nhập
                        isAutoLogin = true;
                        String token = cookie.getValue();
                        String username = JWTUtil.getUserNameFromJwtToken(token);

                        UserDetail userDetail = new UserDetail(username, userService.findRolesByUsername(username));
                        // Lưu tên đăng nhập từ cookie vào session để duy trì trạng thái đăng nhập
                        session.setAttribute("userDetail", userDetail);
                    }
                }
            }
            if (!isAutoLogin) {
                httpResponse.sendRedirect("login?message=" + URLEncoder.encode("Bạn chưa đăng nhập", "UTF-8"));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
 
