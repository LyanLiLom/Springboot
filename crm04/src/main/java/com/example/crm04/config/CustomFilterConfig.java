package com.example.crm04.config;

import com.example.crm04.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFilterConfig {
    @Bean
    public FilterRegistrationBean<CustomFilter> filterConfig(){
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/role","/login"); //Khi ngươời dùng gọi link là /role mới kích hoạt filter
        return registrationBean;
    }

    /**
     * 1) Nếu như đã đăng nhập rồi thì không cần đăng nhập lại
     * Bước 1: Khi đăng nhập thành công thì phải lưu lại thông tin user đã đăng nhập(Session/Cookie)
     * Bước 2: Khi người dùng vào lại trang login thì khiểm tra xem sesion hoặc cookie lưu trữ thông
     * tin người dùng có đang tồn tại hay không.
     * Bước 3: Nếu tồn tại chuyển hướng về trang chủ
     * Bước 4: Nếu không tồn tại thì cho đi vào trang login
     *
     * Hãy làm tính năng phân quyền cho hệ thống CRM
     * - ADMIN: Thêm, xóa sửa ROLE
     * - NHANVIEN: Xem được thông tin nhân viên
     *
     */
}
