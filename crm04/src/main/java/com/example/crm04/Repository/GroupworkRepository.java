package com.example.crm04.Repository;

import com.example.crm04.Entity.GroupworkEntity;
import com.example.crm04.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupworkRepository extends JpaRepository<GroupworkEntity,Integer> {

}
