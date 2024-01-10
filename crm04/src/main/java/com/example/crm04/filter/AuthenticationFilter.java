package com.example.crm04.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Kiem tra authenFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")){
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("Cho phép đi vào role.html");
        }else{
            response.sendRedirect("http://localhost:8080/login");
            System.out.println("Đưa về trang login");
        }

    }
}
