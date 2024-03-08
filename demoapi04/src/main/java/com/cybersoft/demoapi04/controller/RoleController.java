package com.cybersoft.demoapi04.controller;

import com.cybersoft.demoapi04.Service.Imp.RoleServiceImp;
import com.cybersoft.demoapi04.payload.respond.BaseRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImp roleServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getRole(){
        return new ResponseEntity<>(roleServiceImp.getAllRole(), HttpStatus.OK);
    }

    @DeleteMapping("/delete1/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id){
        boolean isSuccess = roleServiceImp.deletedRoleById(id);
        BaseRespond baseRespond = new BaseRespond();

        baseRespond.setStatusCode(200);
        baseRespond.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
        baseRespond.setData(isSuccess);
        return new ResponseEntity<>(baseRespond,HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getRoleAdminG(){
        return new ResponseEntity<>("helloGetadmin", HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> getRoleAdminP(){
        return new ResponseEntity<>("helloPostadmin", HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateRoleAdmin(@PathVariable int id){

        return new ResponseEntity<>("helloUpdateAdmin",HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoleAdmin(@PathVariable int id){
        return new ResponseEntity<>("helloDeleteAdmin",HttpStatus.OK);
    }
    //Viết API xóa role với tham số nhận vào là role_id
    /*

     */
}
