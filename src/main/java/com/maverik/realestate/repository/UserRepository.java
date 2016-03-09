package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.User;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u")
    List<User> findEverThing();

    List<User> findAllByActive(Byte active);

    User findByUsername(String username);

    User findByUsernameAndActive(String username, Byte flag);

    @Modifying
    @Query("Delete from User u where u.id = :userId ")
    void softDeleteUser(@Param("userId") Long userId);

    @Modifying
    @Query("Update User u set u.password = :password where u.id = :userId ")
    Integer changePassword(@Param("userId") Long userId,
	    @Param("password") String password);

}
