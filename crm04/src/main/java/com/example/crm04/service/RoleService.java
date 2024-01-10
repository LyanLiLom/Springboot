package com.example.crm04.service;

import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService { //Tên service sẽ giống tên controller. Bởi vì Service là nơi xử lý code cho controller
   @Autowired
   private RoleRepository roleRepository;
    public List<RolesEntity> getAllRole(){

        return roleRepository.findAll();
    }

    public boolean insertRole(String roleName, String desc){
        boolean flag = false;
        RolesEntity rolesEntity = new RolesEntity();
        if(!roleName.isEmpty()) {
            flag = true;
            rolesEntity.setName(roleName);
            rolesEntity.setDescription(desc);
            roleRepository.save(rolesEntity);
        }

        return flag;
    }
}
