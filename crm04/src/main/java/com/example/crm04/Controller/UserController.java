package com.example.crm04.Controller;

import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Entity.UserEntity;
import com.example.crm04.Repository.UserRepository;
import com.example.crm04.service.RoleService;
import com.example.crm04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/add")
    public String add(Model model){
        List<RolesEntity> rolesEntities = roleService.getAllRole();
        model.addAttribute("rolesEntities", rolesEntities);
        return "user-add";
    }


    @PostMapping("/add")
    public String ProcessAdd(@RequestParam  String name, @RequestParam String email,
                             @RequestParam String password, @RequestParam String phone,
                             Model model, @RequestParam("roleId") int roleId){
        RolesEntity rolesEntity = roleService.getRoleById(roleId);
        System.out.println("Kiểm tra user add" +name+email+password+phone+rolesEntity);
        boolean flag = userService.insertUser(name,email,password,phone,rolesEntity);
        model.addAttribute("flag", flag);
        return "user-add";
    }

    @GetMapping("/table")
    public String table(Model model){
        List<UserEntity> userEntities = userService.getAllUser();

        model.addAttribute("userEntities",userEntities);

        System.out.println("Kiểm tra usertable");
        return "user-table.html";
    }

    @GetMapping("/update/{id}")
    public String Update(@PathVariable int id,Model model){
        UserEntity userEntity = userService.getUserById(id);
        List<RolesEntity> rolesEntities = roleService.getAllRole();
        System.out.println("Kiem tra userUpdate");
        model.addAttribute("rolesEntities",rolesEntities);
        model.addAttribute("userEntity",userEntity);
        return "user-update";
    }

    @PostMapping("/update/{id}")
    public String ProcessUpdate(@PathVariable int id,@RequestParam String fullname,
                                @RequestParam String email,@RequestParam String password,
                                @RequestParam String phone,@RequestParam int roleId ,Model model){
        boolean flag = false;
        if(!email.isEmpty() && !password.isEmpty()){
            flag = true;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setFullname(fullname);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setPhone(phone);
        RolesEntity rolesEntity = roleService.getRoleById(roleId);
        userEntity.setRolesEntity(rolesEntity);
        userService.updateUser(userEntity);

        model.addAttribute("userEntity", userEntity);
        model.addAttribute("flag", flag);
        return "user-update";
    }

    @GetMapping("/delete/{id}")
    public String deleterole(@PathVariable int id){
        userRepository.deleteById(id);
        return "redirect:/user/table";
    }
}
