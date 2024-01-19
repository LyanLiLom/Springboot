package com.example.crm04.service;

import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Entity.UserEntity;
import com.example.crm04.Repository.RoleRepository;
import com.example.crm04.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUser(){

        return userRepository.findAll();
    }


    public boolean insertUser(String name,String email, String password,
                              String phone,RolesEntity rolesEntity){
        boolean flag = false;
        UserEntity userEntity = new UserEntity();
        System.out.println("Kiểm tra user service processadd");
        userEntity.setFullname(name);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setPhone(phone);
        userEntity.setRolesEntity(rolesEntity);

        List<UserEntity> listUsers = userRepository.findAll();
        int count = 0;
        for (UserEntity user: listUsers){
            if (user.getEmail().equalsIgnoreCase(email)){
                count++;
            }
        }
        if(count == 1) {
            flag = true;
            userRepository.save(userEntity);
        }
        return flag;
    }

}
