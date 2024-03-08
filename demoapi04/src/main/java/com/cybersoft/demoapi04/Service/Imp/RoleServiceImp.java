package com.cybersoft.demoapi04.Service.Imp;

import com.cybersoft.demoapi04.entity.RoleEntity;

import java.util.List;

public interface RoleServiceImp {
    List<RoleEntity> getAllRole();

    boolean deletedRoleById(int id);

}
