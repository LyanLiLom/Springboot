package com.example.crm04.Controller;

import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.service.RoleService;
import com.example.crm04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
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
}
