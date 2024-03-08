package com.cybersoft.demoapi04.Service;

import com.cybersoft.demoapi04.Service.Imp.RoleServiceImp;
import com.cybersoft.demoapi04.entity.RoleEntity;
import com.cybersoft.demoapi04.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements RoleServiceImp {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<RoleEntity> getAllRole() {
        return rolesRepository.findAll();
    }

    @Override
    public boolean deletedRoleById(int id) {
        boolean isDelete = false;
        try {
            rolesRepository.deleteById(id);
            isDelete = true;
        } catch (Exception e) {
            System.out.println("Lỗi xóa role:" + e.getMessage());
        }
        return isDelete;
    }
}

