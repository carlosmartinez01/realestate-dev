package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Role;

@Repository(value = "roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

    List<Role> findAllAndByRoleNameNot(String roleName);

}
