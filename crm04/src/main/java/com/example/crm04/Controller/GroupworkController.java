package com.example.crm04.Controller;

import com.example.crm04.Entity.GroupworkEntity;
import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Repository.GroupworkRepository;
import com.example.crm04.service.GroupworkService;
import com.example.crm04.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groupwork")
public class GroupworkController {

    @Autowired
    private GroupworkService groupworkService;


    @GetMapping("/add")
    public String add(){

        return "groupwork-add";
    }

    @PostMapping("/add")
    public String processAdd(@RequestParam String name, @RequestParam String startDate,
                             @RequestParam String endDate, Model model){
        boolean flag = groupworkService.insertGroupwork(name,startDate,endDate);
        model.addAttribute("flag", flag);
        System.out.println("Kiểm tra add groupwork");
        return "groupwork-add";
    }

    @GetMapping("/table")
    public String table(Model model){
        List<GroupworkEntity> groupworkEntities = groupworkService.getAllGroupwork();
        model.addAttribute("groupworks",groupworkEntities);
        System.out.println("Kiểm tra groupwork table");

        return "groupwork.html";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroupwork(@PathVariable int id){
        groupworkService.deleteById(id);
        return "redirect:/groupwork/table";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id,Model model){
        GroupworkEntity groupworkEntity = groupworkService.getGroupworkById(id);
        model.addAttribute("groupworkEntity",groupworkEntity);
        return "groupwork-update.html";
    }

    @PostMapping("/update/{id}")
    public String updateGroupwork (@PathVariable int id, @RequestParam String name
                                    ,@RequestParam String startDate, @RequestParam String endDate,Model model){
        try {
            boolean flag = groupworkService.updateGroupworkById(id, name, startDate, endDate);
            model.addAttribute("flag", flag);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return "groupwork-update.html";
    }
}
