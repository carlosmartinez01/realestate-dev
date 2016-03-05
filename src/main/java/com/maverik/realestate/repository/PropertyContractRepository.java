/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyContract;

/**
 * @author jorge
 *
 */
@Repository
public interface PropertyContractRepository extends
	JpaRepository<PropertyContract, Long> {

    PropertyContract findByPropertyId(Property id);
}
