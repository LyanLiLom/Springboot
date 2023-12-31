package com.example.crm04.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Kiem tra filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        //Kiểm tra xem session lưu trữ ở login lúc đăng nhập thành công có tồn tại hay không
        if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")){
            //Chuyển hướng về trang chủ
            response.sendRedirect("http://localhost:8080");
        }else {
            //Cho đi tiếp vào đường dẫn mà client đang gọi hoặc thoát ra khỏi filter và đi tiếp
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
