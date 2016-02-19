package com.maverik.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Property;

@Repository(value="propertyRepository")
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    List<Property> findByStatus(Byte status);
    
    Property findByName(String name);
}
