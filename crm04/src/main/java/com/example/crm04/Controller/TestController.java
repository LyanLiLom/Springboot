package com.example.crm04.Controller;

import com.example.crm04.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private DateUtils changeDateUtils;
    @GetMapping ("")
    public String test(){
//        return "task";
        return "groupwork";
    }
}
