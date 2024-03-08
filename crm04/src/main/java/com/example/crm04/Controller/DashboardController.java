package com.example.crm04.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DashboardController {
    @GetMapping("")
    public String dashboard(){
        System.out.println("Kiem tra Dashboard");
        return "index.html";
    }

    @GetMapping("/dashboard")
    public String successDashboard(){
        return "index.html";
    }
    @GetMapping("/user")
    public String successUser(){
        return "user-table.html";
    }
    @GetMapping("/role")
    public String successRole(){
        return "role-table.html";
    }
    @GetMapping("/groupwork")
    public String successGroupWork(){
        return "groupwork.html";
    }
    @GetMapping("/task")
    public String successTask(){
        return "task.html";
    }
}
