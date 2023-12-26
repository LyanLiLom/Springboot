package com.example.crm04.Repository;

import com.example.crm04.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RolesEntity,Integer> {

}
