package com.example.crm04.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/cookie")
public class DemoCookieController {

    @GetMapping("")
    public String creatCookie(HttpServletResponse response, HttpServletRequest request){
        //tao cookie
//        Cookie cookie = new Cookie("hello","cookiene");
//        Cookie cookie1 = new Cookie("username", URLEncoder.encode("Nguyen VĂn A", StandardCharsets.UTF_8));
//
//        //sever bắt client tạo cookie
//        response.addCookie(cookie);
//        response.addCookie(cookie1);


        //Lấy toàn bộ cookie client truyền lên
        Cookie[] cookies = request.getCookies();

        //Duyệt qua từng cookie
        for (Cookie cookie : cookies)
        {
            //lấy tên cookie đang duyệt đến
            String name = cookie.getName();
            //lấy giá trị cookie đang duyêt đến
            String value = cookie.getValue();

            if(name.equals("hello")) {
                System.out.println("Kiểm tra name: " + name);
                System.out.println("Kiểm tra value: " + value);
            }
        }
        return "login";
    }
}
