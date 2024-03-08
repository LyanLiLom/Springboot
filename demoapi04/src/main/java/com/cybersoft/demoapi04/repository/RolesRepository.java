package com.cybersoft.demoapi04.repository;

import com.cybersoft.demoapi04.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity,Integer> {
}
