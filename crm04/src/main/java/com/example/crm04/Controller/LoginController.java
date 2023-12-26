package com.example.crm04.Controller;

import com.example.crm04.Entity.UserEntity;
import com.example.crm04.Repository.UserRepository;
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
    public String login(){
        List<UserEntity> list = userRepository.findByEmailAndPassword("nguyenvana@gmail.com","123456");
        for(UserEntity item: list){
            System.out.println("Kiem tra " + item.getEmail());
        }
        return"login";
    }

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
    public String checklogin(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
        boolean flag = false;
        List<UserEntity> list = userRepository.findByEmailAndPassword(email, password);
        System.out.println("Username: " + email);
        System.out.println("Password: " + password);
        if (list.size() > 0) {
            flag = true;
        }
        else {
            flag = false;
            model.addAttribute("flag",flag);
            return "login";
        }
        return "index";
    }


}
