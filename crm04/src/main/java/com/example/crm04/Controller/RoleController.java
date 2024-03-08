package com.example.crm04.Controller;

import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Repository.RoleRepository;
import com.example.crm04.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    /**
     *Bước 1:Xác định nghiệp vụ cho chức năng
     * Bước 2:Xác định câu truy vấn
     * Bước 3: Xác định lượng tham số sẽ sử dụng trong controller
     * Bước 4: Để thực hiện được câu truy vấn liên quan tới bảng đã xác định ở bước 2 ==> tạo ra file repository
     * để quản lý các câu truy vấn chưa có.
     * Bước 5: Xác định hàm tương ứng với lại câu bước 2 của JPA
     *
     */

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    /**
     *save() save có 2 chức năng vừa là thêm dữ liệu vừa là cập nhật dữ liệu
     * -Thêm mới : khóa chính của class entity truyền vào hàm save() không có giá trị
     * -Cập nhật : khóa chính của class entity truyền vào hàm save() có giá trị
     */
    @GetMapping("/add")
    public String add(){
//        RolesEntity rolesEntity = new RolesEntity();
//        //rolesEntity.setId(4) *cập nhật
//        rolesEntity.setName("Test");
//        rolesEntity.setDescription("Hello test");
//        roleRepository.save(rolesEntity);
        return "role-add.html";
    }
    @PostMapping("/add")
    public String processAdd(@RequestParam String roleName, @RequestParam String desc, Model model){
            boolean flag = roleService.insertRole(roleName, desc);

            model.addAttribute("flag", flag);


//        try {
//
//        }catch (Exception e){
//            //Code bên trong catch chi được chạy khi các đoạn code trong try bị lỗi liên quan đến run time Error
//            System.out.println("Lỗi thêm dữ liệu:" + e.getMessage());
//        }

        /**
         * chỉnh link /role thành /role/add: fix lỗi liên quan đến css và js bên file HTML
         * Nếu thêm role thành công thì phải xuất ra màn hình thông báo " thêm thành công" và ngược lại thêm thất bại
         */

        return "role-add.html";
    }

    @GetMapping("/table")
    public String table(Model model){
        List<RolesEntity> rolesEntities = roleService.getAllRole();
        model.addAttribute("roles",rolesEntities);
        System.out.println("Kiểm tra roletable");
        return "role-table.html";
    }
    @GetMapping("/delete/{id}")
    public String deleterole(@PathVariable int id){
        roleRepository.deleteById(id);
        return "redirect:/role/table";
    }

    @GetMapping("/fix/{id}")
    public String fixRole(@PathVariable int id,Model model){

        RolesEntity rolesEntity =  roleService.getRoleById(id);

        model.addAttribute("rolesEntity", rolesEntity);
        return "role-fix.html";
    }

    @PostMapping("/fix/{id}")
    public String updateRole(Model model,@PathVariable int id,
                             @RequestParam String roleName,@RequestParam String desc){
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setId(id);
        rolesEntity.setName(roleName);
        rolesEntity.setDescription(desc);
        roleService.updateRole(rolesEntity);

        model.addAttribute("rolesEntity",rolesEntity);
        return "role-fix.html";
    }
}
