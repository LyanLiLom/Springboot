package com.example.crm04.service;

import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public RolesEntity getRoleById(int id){
        //Optional: Có hoặc không có cũng được
        //Optional chứa các hàm hỗ trợ sẵn giúp kiểm tra giá trị có null hay không để tránh bị
        //lỗi null dữ liệu trong quá trình xử lý
        RolesEntity dataRole = null;
        Optional<RolesEntity> rolesEntity = roleRepository.findById(id);
        //isPresent: Kiểm tra xem biến có giá trị hay không nếu là true tức là biến có giá trị
        //ngược lại false thì sẽ không có giá trị
        if(rolesEntity.isPresent()){
            //.get(): Giúp hủy đi optional đi trả về kiểu dữ liệu thực của biến
            dataRole =  rolesEntity.get();
        }
        System.out.println("Kiểm tra"+ dataRole);
        return dataRole;
    }

    public void updateRole(RolesEntity rolesEntity){
        roleRepository.save(rolesEntity);
    }
}
