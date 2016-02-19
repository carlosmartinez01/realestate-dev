/**
 * 
 */
package com.maverik.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.realestate.domain.entity.Property;
import com.maverik.realestate.domain.entity.PropertyContract;

/**
 * @author jorge
 *
 */
public interface PropertyContractRepository extends
	JpaRepository<PropertyContract, Long> {

    PropertyContract findByPropertyId(Property id);
}
