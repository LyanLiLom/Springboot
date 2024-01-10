package com.example.crm04.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/404")
public class FalseController {

    @GetMapping("")
    public String falseview(){
        return "404.html";
    }

}
