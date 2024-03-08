package com.example.crm04.Controller;

import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Entity.UserEntity;
import com.example.crm04.Repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Các bước cần làm cho một chức năng trong ứng dụng
 * Bước 1: Phân tích yêu cầu của chức năng, tức là phân tích chức năng đó mình cần làm gì và kết quả mong muốn là gì.
 * Bước 2: Xác định được câu truy vấn( query ) giành cho chức năng đó
 * Bước 3: Từ câu truy vấn sẽ xác định được đường dẫn có nhận tham số hay không và số số lượng tham số là bao nhiêu.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String login(HttpServletRequest request,Model model){
//        List<UserEntity> list = userRepository.findByEmailAndPassword("nguyenvana@gmail.com","123456");
//        for(UserEntity item: list){
//            System.out.println("Kiem tra " + item.getEmail());
//        }
        String email = "";
        String password = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    System.out.println("Kiem tra: " + cookie.getValue());
                    email = cookie.getValue();
                }

                if (cookie.getName().equals("password")) {
                    System.out.println("Kiem tra: " + cookie.getValue());
                    password = cookie.getValue();

                }
            }


            model.addAttribute("password", password);
            model.addAttribute("email", email);
        }
        return "login";
    }

//        @GetMapping("")
//        public String check(@RequestParam("email") String email,
//                            @RequestParam("password") String password){
//
//            List<UserEntity> list = userRepository.findByEmailAndPassword(email, password);
//            System.out.println("Username: " + email);
//            System.out.println("Password: " + password);
//
//
//            return "redirect:/login";
//        }

    /**
     * Hoàn thiện chức năng login
     * Bước 1: Thế tham số người dùng truyền vào hàm findByEmailAndPassword
     * Bước 2: kiểm tra xem list có dữ liệu hay không ?
     * Bước 3: Nếu có thì trả ra chuyển qia trong dashboard (Lưu tạo link /dashboard sử dụng page index.html).
     * Bước 4: Nếu thất bại thì xuất thông báo "Đăng nhập thất bại" ra màn hình login
     * Lưu Ý: Phương thức post => Chỉnh form data bên giao diện login
     */

    //model: cho phép trả giá trị từ java sang file view.
    @PostMapping("/dashboard")
    public String checklogin(Model model, @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam(required = false) String remember,
                             HttpServletResponse response, HttpSession httpSession) {

        boolean flag = false;
        String roleId = "";
        List<UserEntity> list = userRepository.findByEmailAndPassword(email, password);
        for (UserEntity check: list){
            String role = check.getRolesEntity().getDescription();
            System.out.println("Kiem tra " + role);
            roleId = role;
        }

        System.out.println("Username: " + email);
        System.out.println("Password: " + password);
        if (list.size() > 0) {
            System.out.println("dang nhap thanh cong");
            flag = true;
            System.out.println("Kiem tra: " + remember);
            try {
                if(remember.equalsIgnoreCase("on")){
//                    Cookie emailcookie = new Cookie("email", email);
//                    Cookie passwordcookie = new Cookie("password", password);
//                    response.addCookie(emailcookie);
//                    response.addCookie(passwordcookie);
                    httpSession.setAttribute("email",email);
//                    httpSession.setAttribute("");
                    httpSession.setMaxInactiveInterval(8*60*60);
                    httpSession.setAttribute("roleId",roleId);
                    httpSession.setMaxInactiveInterval(8*60*60);
                }
            } catch (Exception e ) {
                return "index";
            }
        }
        else {
            flag = false;
            model.addAttribute("flag",flag);
            return "login";
        }
        return "index";
    }

    /**
     * Khi  đăng nhập thành công thì lưu email và mật khẩu vào cookie
     * Khi người dùng  vô lại link / login thì sẽ điền sẵn tên đăng nhập và mật khẩu
     */

}
