package com.armaan.examserver.user.repository;

import com.armaan.examserver.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {


}
