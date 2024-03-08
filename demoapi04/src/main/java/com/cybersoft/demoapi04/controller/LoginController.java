package com.cybersoft.demoapi04.controller;

import com.cybersoft.demoapi04.Service.Imp.LoginServiceImp;
import com.cybersoft.demoapi04.payload.respond.BaseRespond;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     *Do password lưu trữ trong database là chuỗi mã hóa dạng BCrypt cho nên không thể dùng password
     * như điều kiện where
     * Bước 1: Viết câu truy vấn lấu dữ liệu dựa trên username
     * Bước 2: Lấy dữ liệu password trả ra từ bước 1 và kiểm tra xem password lưu trữ trong database
     * với password người dùng truyền lên.
     * Bước 3: Nêếu 2 password match với nhau thì sẽ tạo ra token, nếu không giống thì báo đăng nhập thất bại
     *
     */

    @Autowired
    LoginServiceImp loginServiceImp;
    @PostMapping("")
    public ResponseEntity<?> login(@RequestParam String username,@RequestParam String password){

        //Sinh ra key 1 lần duy nhất, sau khi có key thì bỏ.
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());// Biến key từ dạng secret về dạng String để lưu trữ vào application.properties
//        System.out.println("Kiểm tra key " + key);

        String token = loginServiceImp.checkLogin(username,password);

        BaseRespond baseRespond = new BaseRespond();
        baseRespond.setData(token);


        return new ResponseEntity<>(baseRespond, HttpStatus.OK);
    }
}
