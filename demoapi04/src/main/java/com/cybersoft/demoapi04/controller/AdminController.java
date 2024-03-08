package com.cybersoft.demoapi04.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("")
    public ResponseEntity<?> getAdmin(){
        return new ResponseEntity<>("GET ADMIN", HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> postAdmin(){
        return new ResponseEntity<>("POST ADMIN", HttpStatus.OK);
    }
    @DeleteMapping("")
    public ResponseEntity<?> deleteAdmin(){
        return new ResponseEntity<>("DELETE ADMIN", HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?> putAdmin(){
        return new ResponseEntity<>("PUT ADMIN", HttpStatus.OK);
    }
}
