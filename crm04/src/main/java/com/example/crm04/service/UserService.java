package com.example.crm04.service;

import com.example.crm04.Entity.RolesEntity;
import com.example.crm04.Entity.UserEntity;
import com.example.crm04.Repository.RoleRepository;
import com.example.crm04.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUser(){

        return userRepository.findAll();
    }

    public boolean checkEmailExist(String email){
        boolean flag = false;
        List<UserEntity> listUsers = userRepository.findAll();
        int count = 0;
        for (UserEntity user: listUsers){//kiểm tra xem user đã tồn tại hay chưa, nếu trùng email rồi thì count > 0 thì sẽ không tạo user mới
            if (user.getEmail().equalsIgnoreCase(email)){
                count++;
            }
        }
        if(count == 0) {
            flag = true;

        }
        System.out.println(count);
        return flag;
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
        checkEmailExist(email);
        if(flag == true) {
            userRepository.save(userEntity);
        }
        return flag;
    }

    public UserEntity getUserById(int id){
        UserEntity dataUser = null;
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(userEntity.isPresent()){
            dataUser = userEntity.get();
        }
        System.out.println("Kiểm tra dataUser " +dataUser);
        return dataUser;
    }


    public void updateUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }


}
