package com.maverik.realestate.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.UserRoles;

@Repository(value="userRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
	
	@Modifying
	@Query("Delete from UserRoles ur where ur.id in :userRoles ")
	void deleteUserRolesWithAssociation(@Param("userRoles") Set<Long> userRoles);

}
